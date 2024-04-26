package com.jeferro.products.shared.infrastructure.rest;

import com.jeferro.products.shared.infrastructure.integrations.rest.configurations.JwtProperties;
import com.jeferro.products.shared.infrastructure.integrations.rest.services.AuthJwtService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({
        AuthJwtService.class
})
@EnableConfigurationProperties({
        JwtProperties.class
})
public class RestTestConfiguration {


}
