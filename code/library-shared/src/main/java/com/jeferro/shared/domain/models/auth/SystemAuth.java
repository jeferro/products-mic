package com.jeferro.shared.domain.models.auth;

public class SystemAuth extends Auth {

    public SystemAuth() {
        super();
    }

    public static SystemAuth create() {
        return new SystemAuth();
    }

    @Override
    public String who() {
        return "system";
    }
}
