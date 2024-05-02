package com.jeferro.products.shared.infrastructure.adapters.rest;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.components.rest.shared.securtiy.configurations.RestSecurityProperties;
import com.jeferro.products.components.rest.shared.securtiy.services.JwtDecoder;
import com.jeferro.products.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.context.ActiveProfiles;

@Import({
	AuthRestResolver.class,
	JwtDecoder.class
})
@EnableConfigurationProperties({
	RestSecurityProperties.class
})
@ActiveProfiles(RestProfile.NAME)
public class RestControllerIT {

}
