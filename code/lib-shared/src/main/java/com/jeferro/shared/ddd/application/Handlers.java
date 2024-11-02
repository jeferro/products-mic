package com.jeferro.shared.ddd.application;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.InternalErrorException;

public class Handlers {

  private final Map<Class<Params<?>>, Handler<?, ?>> handlers;

  public Handlers() {
	handlers = new HashMap<>();
  }

  public void registryHandler(Handler<?, ?> handler) {
	Type type = handler.getClass().getGenericSuperclass();

	if (!(type instanceof ParameterizedType parameterizedType)) {
	  throw new IllegalArgumentException("Handler superclass is not a parameterized type");
	}

	Class<Params<?>> paramsClass = (Class<Params<?>>) parameterizedType.getActualTypeArguments()[0];

	handlers.put(paramsClass, handler);
  }

  public <R> Handler<Params<R>, R> getHandler(Params<R> params) {
	Class<?> paramsClass = params.getClass();

	if (!handlers.containsKey(paramsClass)) {
	  throw InternalErrorException.createOfHandlerNotFound(paramsClass.getSimpleName());
	}

	return (Handler<Params<R>, R>) handlers.get(paramsClass);
  }
}
