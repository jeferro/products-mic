package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.product_reviews.application.params.ListProductReviewParams;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListProductReviewHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private ListProductReviewHandler listProductReviewHandler;

  @BeforeEach
  public void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();

	listProductReviewHandler = new ListProductReviewHandler(productReviewsInMemoryRepository);
  }

  @Test
  void givenProductReviews_whenListProductReviews_thenReturnsProductReviews() {
	var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

	var params = new ListProductReviewParams(
		AuthMother.user(),
		userReviewOfApple.getProductId()
	);

	var result = listProductReviewHandler.handle(params);

	assertEquals(1, result.size());
	assertTrue(result.contains(userReviewOfApple));
  }

  @Test
  void givenNoProductReviews_whenListProductReviews_thenReturnsEmptyList() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();

	var params = new ListProductReviewParams(
		AuthMother.user(),
		userReviewOfApple.getProductId()
	);

	var result = listProductReviewHandler.handle(params);

	assertTrue(result.isEmpty());
  }

  private ProductReview givenAnUserProductReviewOfAppleInDatabase() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	return userReviewOfApple;
  }

}