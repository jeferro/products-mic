package com.jeferro.shared.infrastructure.handler_bus;

import java.util.Set;

import com.jeferro.shared.application.Handler;
import com.jeferro.shared.application.HandlerBus;
import com.jeferro.shared.domain.models.auth.AnonymousAuth;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.UserAuth;
import com.jeferro.shared.domain.models.auth.Username;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringHandlerBus extends HandlerBus {

    public SpringHandlerBus(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(Handler.class)
                .values()
                .forEach(this::registryHandler);
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
}
