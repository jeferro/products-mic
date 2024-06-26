package com.jeferro.products.product_reviews.infrastructure.adapters.products_kafka;

import com.jeferro.products.components.kafka.products.BaseProductsKafkaListener;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.product_reviews.application.commands.DeleteAllProductReviewsOfProductCommand;
import com.jeferro.products.products.infrastructure.adapters.kafka.mappers.ProductIdKafkaMapper;
import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.domain.models.auth.SystemAuth;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewsProductsKafkaListener extends BaseProductsKafkaListener {

  private static final SystemAuth systemAuth = SystemAuth.create("products");

  private final ProductIdKafkaMapper productIdKafkaMapper = ProductIdKafkaMapper.INSTANCE;

  private final HandlerBus handlerBus;

  public ProductReviewsProductsKafkaListener(HandlerBus handlerBus) {
	this.handlerBus = handlerBus;
  }

  @Override
  public String getGroupId() {
	return "product-reviews";
  }

  @Override
  protected void consume(ProductDeletedAvroDTO productDeletedAvroDTO) {
	var command = new DeleteAllProductReviewsOfProductCommand(
		systemAuth,
		productIdKafkaMapper.toDomain(productDeletedAvroDTO.getProductId().toString())
	);

	handlerBus.execute(command);
  }
}
