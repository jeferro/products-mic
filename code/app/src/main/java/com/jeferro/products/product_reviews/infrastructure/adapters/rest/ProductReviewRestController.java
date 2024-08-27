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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductReviewRestController implements ProductReviewsApi {

  private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

  private final ProductReviewRestMapper productReviewRestMapper = ProductReviewRestMapper.INSTANCE;

  private final ProductReviewIdRestMapper productReviewIdRestMapper = ProductReviewIdRestMapper.INSTANCE;

  private final HandlerBus handlerBus;

  public ProductReviewRestController(HandlerBus handlerBus) {
	this.handlerBus = handlerBus;
  }

  @Override
  public ResponseEntity<List<ProductReviewRestDTO>> listProductReviews(String productId) {
	var params = new ListProductReviewParams(
		productIdRestMapper.toDomain(productId)
	);

	var productReviews = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReviews);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> createProductReview(String productId,
	  CreateProductReviewInputRestDTO createProductReviewInputRestDTO) {
	var params = new CreateProductReviewParams(
		productIdRestMapper.toDomain(productId),
		createProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> getProductReview(String productId, String username) {
	var params = new GetProductReviewParams(
		productReviewIdRestMapper.toDomain(productId, username)
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> updateProductReview(String productId, String username,
	  UpdateProductReviewInputRestDTO updateProductReviewInputRestDTO) {
	var params = new UpdateProductReviewParams(
		productReviewIdRestMapper.toDomain(productId, username),
		updateProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> deleteProductReview(String productId, String username) {
	var params = new DeleteProductReviewParams(
		productReviewIdRestMapper.toDomain(productId, username)
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }
}
