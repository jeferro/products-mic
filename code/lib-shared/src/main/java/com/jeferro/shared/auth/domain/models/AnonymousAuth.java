package com.jeferro.shared.auth.domain.models;

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
}
