package com.jeferro.products.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.products.product_reviews.application.params.ListProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.application.ContextMother;
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
	var userContext = ContextMother.user();
	var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

	var params = new ListProductReviewParams(
		userReviewOfApple.getProductCode()
	);

	var result = listProductReviewHandler.execute(userContext, params);

	assertEquals(1, result.size());
	assertTrue(result.contains(userReviewOfApple));
  }

  @Test
  void givenNoProductReviews_whenListProductReviews_thenReturnsEmptyList() {
	var userContext = ContextMother.user();
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();

	var params = new ListProductReviewParams(
		userReviewOfApple.getProductCode()
	);

	var result = listProductReviewHandler.execute(userContext, params);

	assertTrue(result.isEmpty());
  }

  private ProductReview givenAnUserProductReviewOfAppleInDatabase() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	return userReviewOfApple;
  }

}