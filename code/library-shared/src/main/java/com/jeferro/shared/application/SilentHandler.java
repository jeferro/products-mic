package com.jeferro.shared.application;

import com.jeferro.shared.application.commands.Command;

public abstract class SilentHandler<P extends Command<R>, R> extends Handler<P, R> {

    public SilentHandler() {
        super();
    }
}
