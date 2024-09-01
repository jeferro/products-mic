package com.jeferro.shared.infrastructure.handler_bus;

import com.jeferro.shared.application.Context;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.application.HandlerBus;
import com.jeferro.shared.infrastructure.adapters.security.services.ContextManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringHandlerBus extends HandlerBus {

  public SpringHandlerBus(ApplicationContext applicationContext) {

	applicationContext.getBeansOfType(Handler.class)
		.values()
		.forEach(this::registryHandler);
  }

  @Override
  protected Context getContext() {
	var auth = ContextManager.getAuth();
	var locale = ContextManager.getLocale();

	return new Context(auth, locale);
  }
}
