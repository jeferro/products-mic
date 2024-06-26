package com.jeferro.products.shared.infrastructure.adapters.rest;

import com.jeferro.products.components.rest.shared.securtiy.configurations.RestSecurityProperties;
import com.jeferro.products.components.rest.shared.securtiy.services.JwtDecoder;
import com.jeferro.products.shared.application.StubHandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({
	AuthRestResolver.class,
	JwtDecoder.class,
	ErrorRestController.class,
	StubHandlerBus.class
})
@EnableConfigurationProperties({
	RestSecurityProperties.class
})
public class RestControllerTest {

  protected static final String AUTHORIZATION_USER_CONTENT =
	  "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwcm9kdWN0cyIsImlhdCI6MTcxNjMyMDgzMiwic3ViIjoidXNlciIsInJvbGVzIjpbInVzZXIiXX0"
          + ".3WW3ZPZVsCsn7P4ZRocUW3Q6Rtjbg4nTc52eBkVFxM4l7qvTitGCcYfmmEOyw66OtswUFhJ0Pk6OJUumfKwYcw";
}
