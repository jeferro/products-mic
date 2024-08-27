package com.jeferro.products.product_reviews.infrastructure.adapters.rest;

import java.util.List;

import com.jeferro.products.components.rest.generated.apis.ProductReviewsApi;
import com.jeferro.products.components.rest.generated.dtos.CreateProductReviewInputRestDTO;
import com.jeferro.products.components.rest.generated.dtos.ProductReviewRestDTO;
import com.jeferro.products.components.rest.generated.dtos.UpdateProductReviewInputRestDTO;
import com.jeferro.products.product_reviews.application.params.CreateProductReviewParams;
import com.jeferro.products.product_reviews.application.params.DeleteProductReviewParams;
import com.jeferro.products.product_reviews.application.params.GetProductReviewParams;
import com.jeferro.products.product_reviews.application.params.ListProductReviewParams;
import com.jeferro.products.product_reviews.application.params.UpdateProductReviewParams;
import com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers.ProductReviewIdRestMapper;
import com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers.ProductReviewRestMapper;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductIdRestMapper;
import com.jeferro.shared.application.HandlerBus;
import com.jeferro.shared.infrastructure.adapters.rest.services.AuthRestResolver;
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
  public ResponseEntity<List<ProductReviewRestDTO>> listProductReviews(String productId) {
	var params = new ListProductReviewParams(
		authRestResolver.resolve(),
		productIdRestMapper.toDomain(productId)
	);

	var productReviews = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReviews);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> createProductReview(String productId,
	  CreateProductReviewInputRestDTO createProductReviewInputRestDTO) {
	var params = new CreateProductReviewParams(
		authRestResolver.resolve(),
		productIdRestMapper.toDomain(productId),
		createProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> getProductReview(String productId, String username) {
	var params = new GetProductReviewParams(
		authRestResolver.resolve(),
		productReviewIdRestMapper.toDomain(productId, username)
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> updateProductReview(String productId, String username,
	  UpdateProductReviewInputRestDTO updateProductReviewInputRestDTO) {
	var params = new UpdateProductReviewParams(
		authRestResolver.resolve(),
		productReviewIdRestMapper.toDomain(productId, username),
		updateProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> deleteProductReview(String productId, String username) {
	var params = new DeleteProductReviewParams(
		authRestResolver.resolve(),
		productReviewIdRestMapper.toDomain(productId, username)
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }
}
