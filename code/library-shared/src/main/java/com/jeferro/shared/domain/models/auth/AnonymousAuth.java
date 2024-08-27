package com.jeferro.shared.domain.models.auth;

public class AnonymousAuth extends Auth {

    public AnonymousAuth() {
        super();
    }

    public static AnonymousAuth create() {
        return new AnonymousAuth();
    }

    @Override
    public String who() {
        return "anonymous";
    }

    @Override
    public boolean isUser() {
        return false;
    }
}
