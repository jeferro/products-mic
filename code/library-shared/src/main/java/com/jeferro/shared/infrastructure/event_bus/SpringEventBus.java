package com.jeferro.shared.infrastructure.event_bus;

import com.jeferro.shared.domain.events.EventBus;
import com.jeferro.shared.domain.events.EventBusPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringEventBus extends EventBus {

    public SpringEventBus(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(EventBusPublisher.class)
                .values()
                .forEach(this::registryPublisher);
    }
}
