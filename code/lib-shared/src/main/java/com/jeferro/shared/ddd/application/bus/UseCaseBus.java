package com.jeferro.shared.ddd.application.bus;

import com.jeferro.shared.ddd.application.UseCase;
import com.jeferro.shared.ddd.application.Handlers;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.ConflictException;
import com.jeferro.shared.ddd.domain.exceptions.InternalErrorException;
import com.jeferro.shared.ddd.domain.exceptions.NotFoundException;
import com.jeferro.shared.ddd.domain.exceptions.auth.ForbiddenException;
import com.jeferro.shared.ddd.domain.models.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public abstract class UseCaseBus {

    private static final Logger logger = LoggerFactory.getLogger(UseCaseBus.class);

    protected final Handlers handlers;

    public UseCaseBus() {
        handlers = new Handlers();
    }

    public <R> R execute(Params<R> params) {
        Instant startAt = Instant.now();

        Context context = null;
        UseCase<Params<R>, R> useCae = null;

        try {
            context = createContext();
            useCae = handlers.findUseCase(params);

            ensurePermissions(context, useCae);

            R result = useCae.execute(context, params);

            logSuccessExecution(startAt, context, useCae, params, result);

            return result;
        } catch (Exception cause) {
            logErrorExecution(startAt, context, useCae, params, cause);

            if (cause instanceof NotFoundException
                    || cause instanceof ConflictException
                    || cause instanceof ForbiddenException) {
                throw cause;
            }

            throw InternalErrorException.createOf(cause);
        }
    }

    protected abstract Context createContext();

    private void ensurePermissions(Context context, UseCase<?, ?> useCase) {
        var auth = context.getAuth();
        var mandatoryRoles = useCase.getMandatoryUserRoles();

        if (!auth.hasRoles(mandatoryRoles)) {
            throw ForbiddenException.createOf(auth, mandatoryRoles);
        }
    }

    private void logSuccessExecution(
            Instant startAt,
            Context context,
            UseCase<?, ?> useCase,
            Params<?> params,
            Object result
    ) {
        var handlerName = useCase.getClass().getSimpleName();
        var auth = context.getAuth();
        var duration = calculateDuration(startAt);

        logger.info("""
                \n\t Duration: {}\s
                \t Auth: {}\s
                \t Handler: {}\s
                \t Params: {}\s
                \t Result: {}\s
                """, duration, auth, handlerName, params, result);
    }

    private void logErrorExecution(
            Instant startAt,
            Context context,
            UseCase<?, ?> useCase,
            Params<?> params,
            Exception cause
    ) {
        var auth = context != null
                ? context.getAuth()
                : null;

        var handlerName = useCase != null
                ? useCase.getClass().getSimpleName()
                : "--";

        var duration = calculateDuration(startAt);

        logger.error("""
                \n\t Duration: {}\s
                \t Auth: {}\s
                \t Handler: {}\s
                \t Params: {}
                """, duration, auth, handlerName, params, cause);
    }

    private Duration calculateDuration(Instant startAt) {
        Instant endAt = Instant.now();

        return Duration.between(startAt, endAt);
    }
}
