package com.jeferro.shared.ddd.application;

public abstract class SilentHandler<P extends Params<R>, R> extends Handler<P, R> {

    public SilentHandler() {
        super();
    }
}
