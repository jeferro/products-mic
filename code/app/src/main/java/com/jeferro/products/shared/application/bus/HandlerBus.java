package com.jeferro.products.shared.application.bus;

import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.domain.exceptions.internals.InternalErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class HandlerBus {

    private static final Logger logger = LoggerFactory.getLogger(HandlerBus.class);

    private final Map<Class<Command<?>>, Handler<?, ?>> handlers = new HashMap<>();

    public <R> R execute(Command<R> command) {
        var handler = getHandler(command);

        if (handler == null) {
            logger.error("Handler not found by command {}", command.getClass().getSimpleName());

            throw InternalErrorException.createOfHandlerNotFound();
        }

        return handler.execute(command);
    }

    protected void registryHandler(Handler<?, ?> handler) {
        Type type = handler.getClass().getGenericSuperclass();

        if (!(type instanceof ParameterizedType parameterizedType)) {
            throw new IllegalArgumentException("Handler superclass is not a parameterized type");
        }

        Class<Command<?>> commandClass = (Class<Command<?>>) parameterizedType.getActualTypeArguments()[0];

        handlers.put(commandClass, handler);
    }

    private <R> Handler<Command<R>, R> getHandler(Command<R> command) {
        Class<?> commandClass = command.getClass();

        if (!handlers.containsKey(commandClass)) {
            return null;
        }

        return (Handler<Command<R>, R>) handlers.get(commandClass);
    }
}
