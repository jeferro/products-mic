package com.jeferro.shared.ddd.application;

import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.InternalErrorException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Handlers {

    private final Map<Class<Params<?>>, UseCase<?, ?>> handlers;

    public Handlers() {
        handlers = new HashMap<>();
    }

    public void registryHandler(UseCase<?, ?> useCase) {
        Type type = useCase.getClass().getGenericSuperclass();

        if (!(type instanceof ParameterizedType parameterizedType)) {
            throw new IllegalArgumentException("Handler superclass is not a parameterized type");
        }

        Class<Params<?>> paramsClass = (Class<Params<?>>) parameterizedType.getActualTypeArguments()[0];

        handlers.put(paramsClass, useCase);
    }

    public <R> UseCase<Params<R>, R> findUseCase(Params<R> params) {
        Class<?> paramsClass = params.getClass();

        if (!handlers.containsKey(paramsClass)) {
            throw InternalErrorException.createOfHandlerNotFound(paramsClass.getSimpleName());
        }

        return (UseCase<Params<R>, R>) handlers.get(paramsClass);
    }
}
