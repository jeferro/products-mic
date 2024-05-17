package com.jeferro.products.shared.application;

import java.util.Set;

import com.jeferro.products.shared.application.commands.Command;

public abstract class SilentHandler<P extends Command<R>, R> extends Handler<P, R> {

    public SilentHandler(Set<String> mandatoryRoles) {
        super(mandatoryRoles);
    }
}
