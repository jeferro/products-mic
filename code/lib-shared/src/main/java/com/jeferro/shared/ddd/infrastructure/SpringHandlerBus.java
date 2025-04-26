package com.jeferro.shared.ddd.infrastructure;

import com.jeferro.shared.auth.infrastructure.ContextManager;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.application.bus.HandlerBus;
import com.jeferro.shared.ddd.domain.models.context.Context;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringHandlerBus extends HandlerBus {

    public SpringHandlerBus(ApplicationContext applicationContext) {

        applicationContext.getBeansOfType(Handler.class)
                .values()
                .forEach(handlers::registryHandler);
    }

    @Override
    protected Context createContext() {
        var auth = ContextManager.getAuth();
        var locale = ContextManager.getLocale();

        return new Context(auth, locale);
    }
}
