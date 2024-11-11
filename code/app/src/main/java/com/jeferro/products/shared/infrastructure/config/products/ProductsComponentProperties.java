package com.jeferro.products.shared.infrastructure.config.products;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties("components.products")
public class ProductsComponentProperties {

  private String productsTopic;

  private String productReviewsTopic;

  private String productReviewsConsumerGroupId;
}
