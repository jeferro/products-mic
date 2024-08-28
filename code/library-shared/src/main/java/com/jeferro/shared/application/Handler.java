package com.jeferro.shared.application;

import com.jeferro.shared.domain.exceptions.ForbiddenException;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.SystemAuth;
import com.jeferro.shared.domain.models.auth.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public abstract class Handler<P extends Params<R>, R> {

    protected final Logger logger;

    public Handler() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    protected abstract Set<String> getMandatoryUserRoles();

    public R execute(Auth auth, P params) {
        Instant startAt = Instant.now();

        var mandatoryUserRoles = getMandatoryUserRoles();

        try {
            if(! canAuthExecuteHandler(auth, mandatoryUserRoles)) {
                throw ForbiddenException.createOf(auth, mandatoryUserRoles);
            }

            R result = handle(auth, params);

            if (shouldLogExecution()) {
                logSuccessExecution(startAt, auth, params, result);
            }

            return result;
        } catch (Exception cause) {
            logErrorExecution(startAt, auth, params, cause);

            throw cause;
        }
    }

    private boolean shouldLogExecution() {
        return !(this instanceof SilentHandler<P, R>);
    }

    protected abstract R handle(Auth auth, P params);

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
            P params,
            R result
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
            P params,
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
