package com.jeferro.shared.ddd.infrastructure;

import com.jeferro.shared.ddd.domain.events.EventBus;
import com.jeferro.shared.ddd.domain.events.EventBusPublisher;
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
