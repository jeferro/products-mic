package com.jeferro.shared.ddd.application;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jeferro.shared.auth.domain.models.Auth;
import com.jeferro.shared.auth.domain.models.SystemAuth;
import com.jeferro.shared.auth.domain.models.UserAuth;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.application.handlers.SilentHandler;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.ApplicationException;
import com.jeferro.shared.ddd.domain.exceptions.ForbiddenException;
import com.jeferro.shared.ddd.domain.exceptions.internals.InternalErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HandlerBus {

    private static final Logger logger = LoggerFactory.getLogger(HandlerBus.class);

    private final Map<Class<Params<?>>, Handler<?, ?>> handlers = new HashMap<>();

    public <R> R execute(Params<R> params) {
        Instant startAt = Instant.now();

        var context = getContext();
        var auth = context.getAuth();

        try {
            var handler = getHandler(params);

            var mandatoryUserRoles = handler.getMandatoryUserRoles();

            if(! canAuthExecuteHandler(auth, mandatoryUserRoles)) {
                throw ForbiddenException.createOf(auth, mandatoryUserRoles);
            }

            R result = handler.execute(context, params);

            if (!(handler instanceof SilentHandler<Params<R>,R>)) {
                logSuccessExecution(startAt, auth, params, result);
            }

            return result;
        } catch (Exception cause) {
            logErrorExecution(startAt, auth, params, cause);

            if(cause instanceof ApplicationException) {
                throw cause;
            }

            throw InternalErrorException.createOf(cause);
        }
    }

    protected abstract Context getContext();

    protected void registryHandler(Handler<?, ?> handler) {
        Type type = handler.getClass().getGenericSuperclass();

        if (!(type instanceof ParameterizedType parameterizedType)) {
            throw new IllegalArgumentException("Handler superclass is not a parameterized type");
        }

        Class<Params<?>> paramsClass = (Class<Params<?>>) parameterizedType.getActualTypeArguments()[0];

        handlers.put(paramsClass, handler);
    }

    private <R> Handler<Params<R>, R> getHandler(Params<R> params) {
        Class<?> paramsClass = params.getClass();

        if (!handlers.containsKey(paramsClass)) {
            throw InternalErrorException.createOfHandlerNotFound(paramsClass.getSimpleName());
        }

        return (Handler<Params<R>, R>) handlers.get(paramsClass);
    }


    private boolean canAuthExecuteHandler(Auth auth, Set<String> mandatoryUserRoles) {
        if(auth instanceof SystemAuth){
            return true;
        }

        if (auth instanceof UserAuth userAuth) {
            return userAuth.hasAllPermissions(mandatoryUserRoles);
        }

        return mandatoryUserRoles.isEmpty();
    }

    private void logSuccessExecution(
        Instant startAt,
        Auth auth,
        Params<?> params,
        Object result
    ) {
        Duration duration = calculateDuration(startAt);

        logger.info("\n\t Handler: {} "
            + "\n\t auth: {} "
            + "\n\t params: {} "
            + "\n\t result: {}"
            + "\n\t duration: {} \n", getClass().getSimpleName(), auth, params, result, duration);
    }

    private void logErrorExecution(
        Instant startAt,
        Auth auth,
        Params<?> params,
        Exception cause
    ) {
        Duration duration = calculateDuration(startAt);

        logger.error("\n\t Handler: {} "
            + "\n\t auth: {} "
            + "\n\t params: {}"
            + "\n\t duration: {} \n", getClass().getSimpleName(), auth, params, duration, cause);
    }

    private Duration calculateDuration(Instant startAt) {
        Instant endAt = Instant.now();

        return Duration.between(startAt, endAt);
    }
}
