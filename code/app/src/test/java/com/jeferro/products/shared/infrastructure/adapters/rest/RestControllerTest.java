package com.jeferro.products.shared.infrastructure.adapters.rest;

import com.jeferro.products.shared.application.StubHandlerBus;
import com.jeferro.shared.infrastructure.adapters.rest.ErrorRestController;
import com.jeferro.shared.infrastructure.adapters.rest.configurations.RestSecurityProperties;
import com.jeferro.shared.infrastructure.adapters.rest.services.jwt.JwtDecoder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({
	JwtDecoder.class,
	ErrorRestController.class,
	StubHandlerBus.class
})
@EnableConfigurationProperties({
	RestSecurityProperties.class
})
public class RestControllerTest {

  protected static final String ACCEPT_LANGUAGE_EN = "en-US,en;q=0.9";

  protected static final String AUTHORIZATION_USER_TOKEN =
	  "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwcm9kdWN0cyIsImlhdCI6MTcxNjMyMDgzMiwic3ViIjoidXNlciIsInJvbGVzIjpbInVzZXIiXX0"
          + ".3WW3ZPZVsCsn7P4ZRocUW3Q6Rtjbg4nTc52eBkVFxM4l7qvTitGCcYfmmEOyw66OtswUFhJ0Pk6OJUumfKwYcw";
}
