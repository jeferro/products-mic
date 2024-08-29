package com.jeferro.products.products.product_reviews.infrastructure.adapters.rest;

import java.util.List;

import com.jeferro.products.generated.rest.v1.apis.ProductReviewsApi;
import com.jeferro.products.generated.rest.v1.dtos.CreateProductReviewInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductReviewRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductReviewInputRestDTO;
import com.jeferro.products.products.product_reviews.application.params.CreateProductReviewParams;
import com.jeferro.products.products.product_reviews.application.params.DeleteProductReviewParams;
import com.jeferro.products.products.product_reviews.application.params.GetProductReviewParams;
import com.jeferro.products.products.product_reviews.application.params.ListProductReviewParams;
import com.jeferro.products.products.product_reviews.application.params.UpdateProductReviewParams;
import com.jeferro.products.products.product_reviews.infrastructure.adapters.rest.mappers.ProductReviewCodeRestMapper;
import com.jeferro.products.products.product_reviews.infrastructure.adapters.rest.mappers.ProductReviewRestMapper;
import com.jeferro.products.products.products.infrastructure.adapters.rest.mappers.ProductCodeRestMapper;
import com.jeferro.shared.application.HandlerBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductReviewRestController implements ProductReviewsApi {

  private final ProductCodeRestMapper productCodeRestMapper = ProductCodeRestMapper.INSTANCE;

  private final ProductReviewRestMapper productReviewRestMapper = ProductReviewRestMapper.INSTANCE;

  private final ProductReviewCodeRestMapper productReviewCodeRestMapper = ProductReviewCodeRestMapper.INSTANCE;

  private final HandlerBus handlerBus;

  public ProductReviewRestController(HandlerBus handlerBus) {
	this.handlerBus = handlerBus;
  }

  @Override
  public ResponseEntity<List<ProductReviewRestDTO>> listProductReviews(String productCode) {
	var params = new ListProductReviewParams(
		productCodeRestMapper.toDomain(productCode)
	);

	var productReviews = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReviews);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> createProductReview(String productCode,
	  CreateProductReviewInputRestDTO createProductReviewInputRestDTO) {
	var params = new CreateProductReviewParams(
		productCodeRestMapper.toDomain(productCode),
		createProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> getProductReview(String productCode, String username) {
	var params = new GetProductReviewParams(
		productReviewCodeRestMapper.toDomain(productCode, username)
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> updateProductReview(String productCode, String username,
	  UpdateProductReviewInputRestDTO updateProductReviewInputRestDTO) {
	var params = new UpdateProductReviewParams(
		productReviewCodeRestMapper.toDomain(productCode, username),
		updateProductReviewInputRestDTO.getComment()
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }

  @Override
  public ResponseEntity<ProductReviewRestDTO> deleteProductReview(String productCode, String username) {
	var params = new DeleteProductReviewParams(
		productReviewCodeRestMapper.toDomain(productCode, username)
	);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toOkResponseDTO(productReview);
  }
}
