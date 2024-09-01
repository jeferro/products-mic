package com.jeferro.shared.ddd.application;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.jeferro.shared.ddd.domain.exceptions.internals.InternalErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HandlerBus {

    private static final Logger logger = LoggerFactory.getLogger(HandlerBus.class);

    private final Map<Class<Params<?>>, Handler<?, ?>> handlers = new HashMap<>();

    public <R> R execute(Params<R> params) {
        var context = getContext();
        var handler = getHandler(params);

        if (handler == null) {
            logger.error("Handler not found by params {}", params.getClass().getSimpleName());

            throw InternalErrorException.createOfHandlerNotFound();
        }

        return handler.execute(context, params);
    }

    protected abstract Context getContext();

    protected void registryHandler(Handler<?, ?> handler) {
        Type type = handler.getClass().getGenericSuperclass();

        if (!(type instanceof ParameterizedType parameterizedType)) {
            throw new IllegalArgumentException("Handler superclass is not a parameterized type");
        }

        Class<Params<?>> paramsClass = (Class<Params<?>>) parameterizedType.getActualTypeArguments()[0];

        handlers.put(paramsClass, handler);
    }

    private <R> Handler<Params<R>, R> getHandler(Params<R> params) {
        Class<?> paramsClass = params.getClass();

        if (!handlers.containsKey(paramsClass)) {
            return null;
        }

        return (Handler<Params<R>, R>) handlers.get(paramsClass);
    }
}
