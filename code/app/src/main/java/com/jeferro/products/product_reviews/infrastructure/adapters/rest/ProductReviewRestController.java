package com.jeferro.products.product_reviews.infrastructure.adapters.rest;

import com.jeferro.products.components.rest.generated.apis.ProductReviewsApi;
import com.jeferro.products.components.rest.generated.dtos.CreateProductReviewInputRestDTO;
import com.jeferro.products.components.rest.generated.dtos.ProductReviewRestDTO;
import com.jeferro.products.product_reviews.application.commands.CreateProductReviewCommand;
import com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers.ProductReviewRestMapper;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductIdRestMapper;
import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.products.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductReviewRestController implements ProductReviewsApi {

  private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

  private final ProductReviewRestMapper productReviewRestMapper = ProductReviewRestMapper.INSTANCE;

  private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

  private final AuthRestResolver authRestResolver;

  private final HandlerBus handlerBus;

  public ProductReviewRestController(AuthRestResolver authRestResolver,
	  HandlerBus handlerBus) {
	this.authRestResolver = authRestResolver;
	this.handlerBus = handlerBus;
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> createProductReview(String productId, String username,
	  CreateProductReviewInputRestDTO createProductReviewInputRestDTO) {
	var command = new CreateProductReviewCommand(
		authRestResolver.resolve(),
		usernameRestMapper.toDomain(username),
		productIdRestMapper.toDomain(productId),
		createProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(command);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> deleteProductReview(String productId, String username) {
	return ProductReviewsApi.super.deleteProductReview(productId, username);
  }
}
