package com.jeferro.shared.infrastructure.handler_bus;

import com.jeferro.shared.application.Handler;
import com.jeferro.shared.application.HandlerBus;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.infrastructure.adapters.security.services.SecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringHandlerBus extends HandlerBus {

  private final SecurityManager securityManager;

  public SpringHandlerBus(ApplicationContext applicationContext, SecurityManager securityManager) {
	this.securityManager = securityManager;

	applicationContext.getBeansOfType(Handler.class)
		.values()
		.forEach(this::registryHandler);
  }

  @Override
  protected Auth getContext() {
	return securityManager.getAuth();
  }
}
