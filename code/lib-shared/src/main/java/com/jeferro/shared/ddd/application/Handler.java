package com.jeferro.shared.ddd.application;

import java.util.Set;

import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.models.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Handler<P extends Params<R>, R> {

    protected final Logger logger;

    public Handler() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public abstract Set<String> getMandatoryUserRoles();

    public abstract R execute(Context context, P params);

}
