package com.jeferro.products.shared.application;

import com.jeferro.shared.application.bus.HandlerBus;
import com.jeferro.shared.application.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class StubHandlerBus extends HandlerBus {

    private final List<Command<?>> commands;

    private Object result;

    public StubHandlerBus() {
        commands = new ArrayList<>();
        result = null;
    }

    @Override
    public <R> R execute(Command<R> command) {
        commands.add(command);

        return (R) result;
    }

    public void init(Object result) {
        commands.clear();

        this.result = result;
    }

    public int size() {
        return commands.size();
    }

    public Command<?> getFirstCommandOrError() {
        return commands.stream()
                .findFirst()
                .orElseThrow();
    }
}
