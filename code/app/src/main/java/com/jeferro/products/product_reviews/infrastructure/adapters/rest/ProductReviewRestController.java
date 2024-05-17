package com.jeferro.products.product_reviews.infrastructure.adapters.rest;

import java.util.List;

import com.jeferro.products.components.rest.generated.apis.ProductReviewsApi;
import com.jeferro.products.components.rest.generated.dtos.CreateProductReviewInputRestDTO;
import com.jeferro.products.components.rest.generated.dtos.ProductReviewRestDTO;
import com.jeferro.products.product_reviews.application.commands.CreateProductReviewCommand;
import com.jeferro.products.product_reviews.application.commands.DeleteProductReviewCommand;
import com.jeferro.products.product_reviews.application.commands.ListProductReviewCommand;
import com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers.ProductReviewIdRestMapper;
import com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers.ProductReviewRestMapper;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductIdRestMapper;
import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductReviewRestController implements ProductReviewsApi {

  private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

  private final ProductReviewRestMapper productReviewRestMapper = ProductReviewRestMapper.INSTANCE;

  private final ProductReviewIdRestMapper productReviewIdRestMapper = ProductReviewIdRestMapper.INSTANCE;

  private final AuthRestResolver authRestResolver;

  private final HandlerBus handlerBus;

  public ProductReviewRestController(AuthRestResolver authRestResolver,
	  HandlerBus handlerBus) {
	this.authRestResolver = authRestResolver;
	this.handlerBus = handlerBus;
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> createProductReview(String productId,
	  CreateProductReviewInputRestDTO createProductReviewInputRestDTO) {
	var command = new CreateProductReviewCommand(
		authRestResolver.resolve(),
		productIdRestMapper.toDomain(productId),
		createProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(command);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> deleteProductReview(String productId, String productReviewId) {
	var command = new DeleteProductReviewCommand(
		authRestResolver.resolve(),
		productIdRestMapper.toDomain(productId),
		productReviewIdRestMapper.toDomain(productReviewId)
	);

	var productReview = handlerBus.execute(command);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<List<ProductReviewRestDTO>> listProductReviews(String productId) {
	var command = new ListProductReviewCommand(
		authRestResolver.resolve(),
		productIdRestMapper.toDomain(productId)
	);

	var productReviews = handlerBus.execute(command);

	return productReviewRestMapper.toOkResponseDTO(productReviews);
  }
}
