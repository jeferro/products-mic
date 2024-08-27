package com.jeferro.shared.infrastructure.handler_bus;

import com.jeferro.shared.application.Handler;
import com.jeferro.shared.application.bus.HandlerBus;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringHandlerBus extends HandlerBus {

    public SpringHandlerBus(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(Handler.class)
                .values()
                .forEach(this::registryHandler);
    }
}
