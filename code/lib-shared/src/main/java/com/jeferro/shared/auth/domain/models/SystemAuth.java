package com.jeferro.shared.auth.domain.models;

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
