package com.jeferro.shared.domain.models.auth;

public class SystemAuth extends Auth {

    private final String name;

    public SystemAuth(String name) {
        super();
        this.name = name;
    }

    public static SystemAuth create(String name) {
        return new SystemAuth(name);
    }

    @Override
    public String who() {
        return name;
    }

    @Override
    public boolean isUser() {
        return false;
    }
}
