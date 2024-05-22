package com.jeferro.products.shared.application;

import java.util.ArrayList;
import java.util.List;

import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.application.commands.Command;

public class StubHandlerBus extends HandlerBus {

  private List<Command<?>> commands;

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
