package com.jeferro.products.shared.application.bus;

import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.application.commands.Command;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class HandlerBus {

    private final Map<Class<Command<?>>, Handler<?, ?>> handlers = new HashMap<>();

    public <R> R execute(Command<R> command) {
        Handler<Command<R>, R> handler = getHandler(command)
                .orElseThrow(() -> HandlerNotFoundException.of(command));

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

    private <R> Optional<Handler<Command<R>, R>> getHandler(Command<R> command) {
        Class<?> commandClass = command.getClass();

        if (!handlers.containsKey(commandClass)) {
            return Optional.empty();
        }

        Handler<Command<R>, R> handler = (Handler<Command<R>, R>) handlers.get(commandClass);

        return Optional.of(handler);
    }
}
