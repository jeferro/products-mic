package com.jeferro.products.shared.infrastructure.handler_bus;

import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.application.bus.HandlerBus;
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
