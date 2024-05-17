package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.product_reviews.application.commands.CreateProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewAlreadyExistsException;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.domain.models.ProductIdMother;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateProductReviewHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private CreateProductReviewHandler createProductReviewHandler;

  @BeforeEach
  public void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();

	createProductReviewHandler = new CreateProductReviewHandler(productReviewsInMemoryRepository);
  }

  @Test
  void givenUserDidNotCommentOnProduct_whenCreateProductReview_thenReturnsNewProductReview() {
	var userAuth = AuthMother.userAuth();
	var productId = ProductIdMother.appleId();
	var comment = "New comment about product";

	var command = new CreateProductReviewCommand(
		userAuth,
		productId,
		comment
	);

	var result = createProductReviewHandler.handle(command);

	assertEquals(userAuth.getUsername(), result.getUsername());
	assertEquals(productId, result.getProductId());
	assertEquals(comment, result.getComment());

	assertEquals(1, productReviewsInMemoryRepository.size());
	assertTrue(productReviewsInMemoryRepository.contains(result));
  }

  @Test
  void givenUserCommentsOnProduct_whenCreateProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	var userAuth = AuthMother.userAuth();

	var command = new CreateProductReviewCommand(
		userAuth,
		userReviewOfApple.getProductId(),
		"other comment"
	);

	assertThrows(ProductReviewAlreadyExistsException.class,
		() -> createProductReviewHandler.handle(command));
  }
}