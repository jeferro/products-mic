package com.jeferro.shared.ddd.application.bus;

import java.time.Duration;
import java.time.Instant;

import com.jeferro.shared.ddd.domain.models.context.Context;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.application.Handlers;
import com.jeferro.shared.ddd.application.SilentHandler;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.ApplicationException;
import com.jeferro.shared.ddd.domain.exceptions.auth.ForbiddenException;
import com.jeferro.shared.ddd.domain.exceptions.InternalErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HandlerBus {

    private static final Logger logger = LoggerFactory.getLogger(HandlerBus.class);

    protected final Handlers handlers;

    public HandlerBus() {
        handlers = new Handlers();
    }

    public <R> R execute(Params<R> params) {
        Instant startAt = Instant.now();

        Context context = null;
        Handler<Params<R>, R> handler = null;

        try {
            context = createContext();
            handler = handlers.getHandler(params);

            ensurePermissions(context, handler);

            R result = handler.execute(context, params);

            logSuccessExecution(startAt, context, handler, params, result);

            return result;
        } catch (Exception cause) {
            logErrorExecution(startAt, context, handler, params, cause);

            if(cause instanceof ApplicationException) {
                throw cause;
            }

            throw InternalErrorException.createOf(cause);
        }
    }

    protected abstract Context createContext();

    private void ensurePermissions(Context context, Handler<?, ?> handler) {
        var auth = context.getAuth();
        var mandatoryRoles = handler.getMandatoryUserRoles();

        if(!auth.hasRoles(mandatoryRoles)) {
            throw ForbiddenException.createOf(auth, mandatoryRoles);
        }
    }

    private void logSuccessExecution(
        Instant startAt,
        Context context,
        Handler<?, ?> handler,
        Params<?> params,
        Object result
    ) {
        if (handler instanceof SilentHandler) {
            return;
        }

		var handlerName = handler.getClass().getSimpleName();
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
		Handler<?, ?> handler,
        Params<?> params,
        Exception cause
    ) {
        var auth = context != null
        	? context.getAuth()
            : null;

	  	var handlerName = handler != null
	  		? handler.getClass().getSimpleName()
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
