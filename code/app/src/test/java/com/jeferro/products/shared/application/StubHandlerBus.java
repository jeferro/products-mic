package com.jeferro.products.shared.application;

import com.jeferro.shared.application.HandlerBus;
import com.jeferro.shared.application.Params;

import java.util.ArrayList;
import java.util.List;

public class StubHandlerBus extends HandlerBus {

    private final List<Params<?>> params;

    private Object result;

    public StubHandlerBus() {
        params = new ArrayList<>();
        result = null;
    }

    @Override
    public <R> R execute(Params<R> params) {
        this.params.add(params);

        return (R) result;
    }

    public void init(Object result) {
        params.clear();

        this.result = result;
    }

    public int size() {
        return params.size();
    }

    public Params<?> getFirstParamOrError() {
        return params.stream()
                .findFirst()
                .orElseThrow();
    }
}
