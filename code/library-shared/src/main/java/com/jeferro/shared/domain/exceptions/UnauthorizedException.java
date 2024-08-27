package com.jeferro.shared.domain.exceptions;

public class UnauthorizedException extends ApplicationException {

    private UnauthorizedException(String message){
        super(SharedExceptionCodes.UNAUTHORIZED.value, "Unauthorized", message);
    }

    public static UnauthorizedException createOf() {
        return new UnauthorizedException("Unauthorized");
    }
}
