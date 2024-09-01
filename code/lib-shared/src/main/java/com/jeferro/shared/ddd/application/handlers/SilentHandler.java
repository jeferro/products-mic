package com.jeferro.shared.ddd.application.handlers;

import com.jeferro.shared.ddd.application.params.Params;

public abstract class SilentHandler<P extends Params<R>, R> extends Handler<P, R> {

    public SilentHandler() {
        super();
    }
}
