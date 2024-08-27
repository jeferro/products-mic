package com.jeferro.shared.domain.exceptions.internals;

import com.jeferro.shared.domain.exceptions.ApplicationException;

import static com.jeferro.shared.domain.exceptions.SharedExceptionCodes.INTERNAL_ERROR;

public class InternalErrorException extends ApplicationException {

    protected InternalErrorException(String message) {
        super(INTERNAL_ERROR.value, "Internal error", message);
    }

    public static InternalErrorException createOfHandlerNotFound() {
        return new InternalErrorException("");
    }
}
