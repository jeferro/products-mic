package com.jeferro.products.shared.infrastructure.rest;

import com.jeferro.products.components.rest.shared.securtiy.configurations.RestSecurityProperties;
import com.jeferro.products.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({
        AuthRestResolver.class
})
@EnableConfigurationProperties({
        RestSecurityProperties.class
})
public class RestTestConfiguration {


}
