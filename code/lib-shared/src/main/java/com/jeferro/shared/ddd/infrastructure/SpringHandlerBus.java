package com.jeferro.shared.ddd.infrastructure;

import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.application.HandlerBus;
import com.jeferro.shared.auth.infrastructure.ContextManager;
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
