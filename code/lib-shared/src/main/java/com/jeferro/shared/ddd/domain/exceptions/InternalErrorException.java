package com.jeferro.shared.ddd.domain.exceptions;

public class InternalErrorException extends ApplicationException {

    protected InternalErrorException(String message) {
        super(SharedExceptionCodes.INTERNAL_ERROR.value, "Internal error", message);
    }

    public static InternalErrorException createOfUseCaseNotFound(String paramsClassname) {
        return new InternalErrorException("Use case not found for params: " + paramsClassname);
    }

    public static InternalErrorException createOf(Exception cause) {
        return new InternalErrorException(cause.getMessage());
    }
}
