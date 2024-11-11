package com.jeferro.products.shared.infrastructure.config.products;

import com.jeferro.products.shared.domain.config.ProductConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties("application.configs")
public class ProductProperties extends ProductConfig {

}
