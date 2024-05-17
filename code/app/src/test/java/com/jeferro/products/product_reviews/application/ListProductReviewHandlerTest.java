package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.product_reviews.application.commands.ListProductReviewCommand;
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
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var command = new ListProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId()
	);
	var result = listProductReviewHandler.handle(command);

	assertEquals(1, result.size());
	assertTrue(result.contains(userReviewOfApple));
  }

  @Test
  void givenNoProductReviews_whenListProductReviews_thenReturnsEmptyList() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var command = new ListProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId()
	);
	var result = listProductReviewHandler.handle(command);

	assertTrue(result.isEmpty());
  }

}