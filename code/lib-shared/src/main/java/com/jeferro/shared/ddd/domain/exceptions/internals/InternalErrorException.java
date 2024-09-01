package com.jeferro.shared.ddd.domain.exceptions.internals;

import com.jeferro.shared.ddd.domain.exceptions.ApplicationException;
import com.jeferro.shared.ddd.domain.exceptions.SharedExceptionCodes;

public class InternalErrorException extends ApplicationException {

    protected InternalErrorException(String message) {
        super(SharedExceptionCodes.INTERNAL_ERROR.value, "Internal error", message);
    }

    public static InternalErrorException createOfHandlerNotFound(String paramsClassname) {
        return new InternalErrorException("Handler not found for params: " + paramsClassname);
    }

    public static InternalErrorException createOf(Exception cause) {
        return new InternalErrorException(cause.getMessage());
    }
}
