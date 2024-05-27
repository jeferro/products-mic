package com.jeferro.products.shared.domain.exceptions;

import static com.jeferro.products.shared.domain.exceptions.SharedExceptionCodes.UNAUTHORIZED;

public class UnauthorizedException extends ApplicationException {

    private UnauthorizedException(String message){
        super(UNAUTHORIZED.value, "Unauthorized", message);
    }

    public static UnauthorizedException createOf() {
        return new UnauthorizedException("Unauthorized");
    }
}
