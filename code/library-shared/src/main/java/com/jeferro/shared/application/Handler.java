package com.jeferro.shared.application;

import com.jeferro.shared.application.commands.Command;
import com.jeferro.shared.domain.exceptions.ForbiddenException;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.SystemAuth;
import com.jeferro.shared.domain.models.auth.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public abstract class Handler<C extends Command<R>, R> {

    protected final Logger logger;

    public Handler() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    protected abstract Set<String> getMandatoryUserRoles();

    public R execute(C command) {
        Instant startAt = Instant.now();

        var auth = command.getAuth();
        var mandatoryUserRoles = getMandatoryUserRoles();

        try {
            if(! canAuthExecuteCommand(auth, mandatoryUserRoles)) {
                throw ForbiddenException.createOf(auth, mandatoryUserRoles);
            }

            R result = handle(command);

            if (shouldLogExecution()) {
                logSuccessExecution(startAt, command, result);
            }

            return result;
        } catch (Exception cause) {
            logErrorExecution(startAt, command, cause);

            throw cause;
        }
    }

    private boolean shouldLogExecution() {
        return !(this instanceof SilentHandler<C, R>);
    }

    protected abstract R handle(C command);

    private boolean canAuthExecuteCommand(Auth auth, Set<String> mandatoryUserRoles) {
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
            C command,
            R result
    ) {
        Duration duration = calculateDuration(startAt);

        logger.info("{} \n\t command: {} \n\t result: {}\n", duration, command, result);
    }

    private void logErrorExecution(
            Instant startAt,
            C command,
            Exception cause
    ) {
        Duration duration = calculateDuration(startAt);

        logger.error("{} \n\t command: {}", duration, command, cause);
    }

    private Duration calculateDuration(Instant startAt) {
        Instant endAt = Instant.now();

        return Duration.between(startAt, endAt);
    }
}
