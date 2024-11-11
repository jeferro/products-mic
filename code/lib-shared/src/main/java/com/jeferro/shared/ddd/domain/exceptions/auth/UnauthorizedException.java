package com.jeferro.shared.ddd.domain.exceptions.auth;

import com.jeferro.shared.ddd.domain.exceptions.ApplicationException;
import com.jeferro.shared.ddd.domain.exceptions.SharedExceptionCodes;

public class UnauthorizedException extends ApplicationException {

    private UnauthorizedException(String message){
        super(SharedExceptionCodes.UNAUTHORIZED.value, "Unauthorized", message);
    }

    public static UnauthorizedException createOf() {
        return new UnauthorizedException("Unauthorized");
    }
}
