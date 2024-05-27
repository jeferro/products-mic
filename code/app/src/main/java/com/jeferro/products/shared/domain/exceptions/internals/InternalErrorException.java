package com.jeferro.products.shared.domain.exceptions.internals;

import com.jeferro.products.shared.domain.exceptions.ApplicationException;
import com.jeferro.products.shared.domain.exceptions.SharedExceptionCodes;

public class InternalErrorException extends ApplicationException {

    protected InternalErrorException(String message) {
        super(SharedExceptionCodes.INTERNAL_ERROR.value, "Internal error", message);
    }

    public static InternalErrorException createOfHandlerNotFound() {
        return new InternalErrorException("");
    }
}
