package com.jeferro.products.shared.application;

import com.jeferro.products.shared.application.commands.Command;

public abstract class SilentHandler<P extends Command<R>, R> extends Handler<P, R> {

    public SilentHandler() {
        super();
    }
}
