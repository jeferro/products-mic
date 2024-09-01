package com.jeferro.shared.ddd.application;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Handler<P extends Params<R>, R> {

    protected final Logger logger;

    public Handler() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    protected abstract Set<String> getMandatoryUserRoles();

    protected abstract R handle(Context context, P params);

}
