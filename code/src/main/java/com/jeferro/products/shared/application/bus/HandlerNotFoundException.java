package com.jeferro.products.shared.application.bus;

import com.jeferro.products.shared.application.Command;
import com.jeferro.products.shared.domain.exceptions.ApplicationException;

public class HandlerNotFoundException extends ApplicationException {

    private HandlerNotFoundException(String message) {
        super(message);
    }

    public static HandlerNotFoundException createOf(Command<?> command) {
        String commandClassName = command.getClass().getSimpleName();

        return new HandlerNotFoundException("Handler not found by command " + commandClassName);
    }
}
