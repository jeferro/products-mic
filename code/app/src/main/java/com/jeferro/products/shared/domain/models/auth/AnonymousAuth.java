package com.jeferro.products.shared.domain.models.auth;

public class AnonymousAuth extends Auth {

    protected AnonymousAuth() {
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
