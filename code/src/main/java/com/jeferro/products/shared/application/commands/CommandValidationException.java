package com.jeferro.products.shared.application.commands;

import com.jeferro.products.shared.domain.exceptions.ApplicationException;

public class CommandValidationException extends ApplicationException {

    private CommandValidationException(String message){
        super(message);
    }

    public static CommandValidationException ofMessage(String message) {
        return new CommandValidationException(message);
    }
}
