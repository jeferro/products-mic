package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.product_reviews.application.params.GetProductReviewParams;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetProductReviewHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private GetProductReviewHandler getProductReviewHandler;

  @BeforeEach
  void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();

	getProductReviewHandler = new GetProductReviewHandler(productReviewsInMemoryRepository);
  }

  @Test
  void givenAProductReview_whenGetProductReview_thenReturnsProductReview() {
	var userAuth = AuthMother.user();
	var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

	var params = new GetProductReviewParams(
		userReviewOfApple.getId()
	);

	var result = getProductReviewHandler.handle(userAuth, params);

	assertEquals(userReviewOfApple, result);
  }

  @Test
  void givenNoProductReview_whenGetProductReview_thenThrowsException() {
	var userAuth = AuthMother.user();
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var params = new GetProductReviewParams(
		userReviewOfApple.getId()
	);

	assertThrows(ProductReviewNotFoundException.class,
		() -> getProductReviewHandler.handle(userAuth, params));
  }

  private ProductReview givenAnUserProductReviewOfAppleInDatabase() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	return userReviewOfApple;
  }
}