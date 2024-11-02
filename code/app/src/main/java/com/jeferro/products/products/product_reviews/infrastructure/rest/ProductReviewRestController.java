package com.jeferro.products.products.product_reviews.infrastructure.rest;

import java.util.List;

import com.jeferro.products.generated.rest.v1.apis.ProductReviewsApi;
import com.jeferro.products.generated.rest.v1.dtos.CreateProductReviewInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductReviewRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductReviewInputRestDTO;
import com.jeferro.products.products.product_reviews.infrastructure.rest.mappers.ProductReviewRestMapper;
import com.jeferro.shared.ddd.application.bus.HandlerBus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductReviewRestController implements ProductReviewsApi {

  private final ProductReviewRestMapper productReviewRestMapper = ProductReviewRestMapper.INSTANCE;

  private final HandlerBus handlerBus;

  @Override
  public List<ProductReviewRestDTO> listProductReviews(String productCode) {
	var params = productReviewRestMapper.toListProductsParams(productCode);

	var productReviews = handlerBus.execute(params);

	return productReviewRestMapper.toDTO(productReviews);
  }

  @Override
  public ProductReviewRestDTO createProductReview(String productCode,
	  CreateProductReviewInputRestDTO inputRestDTO) {
	var params = productReviewRestMapper.toCreateProductReviewParams(productCode, inputRestDTO);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toDTO(productReview);
  }

  @Override
  public ProductReviewRestDTO getProductReview(String productCode, String username) {
	var params = productReviewRestMapper.toGetProductReviewParams(productCode, username);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toDTO(productReview);
  }

  @Override
  public ProductReviewRestDTO updateProductReview(String productCode, String username,
	  UpdateProductReviewInputRestDTO inputRestDTO) {
	var params = productReviewRestMapper.toUpdateProductReviewParams(productCode, username, inputRestDTO);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toDTO(productReview);
  }

  @Override
  public ProductReviewRestDTO deleteProductReview(String productCode, String username) {
	var params = productReviewRestMapper.toDeleteProductReviewParams(productCode, username);

	var productReview = handlerBus.execute(params);

	return productReviewRestMapper.toDTO(productReview);
  }
}
