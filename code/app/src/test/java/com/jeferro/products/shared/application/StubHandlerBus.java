package com.jeferro.products.shared.application;

import com.jeferro.shared.application.HandlerBus;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.models.auth.AnonymousAuth;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.UserAuth;
import com.jeferro.shared.domain.models.auth.Username;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Override
    protected Auth getAuth() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null){
            return AnonymousAuth.create();
        }

        var username = new Username(authentication.getPrincipal().toString());
        var roles = (Set<String>) authentication.getCredentials();

        return UserAuth.create(username, roles);
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
