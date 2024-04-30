package com.jeferro.products.shared.application;

import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public abstract class Handler<C extends Command<R>, R> {

    protected final Logger logger;

    private final Set<String> mandatoryRoles;

    public Handler(Set<String> mandatoryRoles) {
        logger = LoggerFactory.getLogger(this.getClass());

        this.mandatoryRoles = mandatoryRoles;
    }

    public R execute(C command) {
        Instant startAt = Instant.now();

        try {
            ensurePermissions(command);

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

    private void ensurePermissions(C command) {
        var auth = command.getAuth();

        if (auth.hasPermissions(mandatoryRoles)) {
            return;
        }

        throw ForbiddenException.of(auth);
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
