package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.product_reviews.application.commands.UpdateProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateProductReviewHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private UpdateProductReviewHandler updateProductReviewHandler;

  @BeforeEach
  void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();

	updateProductReviewHandler = new UpdateProductReviewHandler(productReviewsInMemoryRepository);
  }

  @Test
  void givenAProductReview_whenUpdateProductReview_thenReturnsUpdatedProductReview() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var newComment = "New comment about apple";
	var command = new UpdateProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId(),
		newComment
	);
	var result = updateProductReviewHandler.handle(command);

	assertEquals(userReviewOfApple.getId(), result.getId());
	assertEquals(newComment, userReviewOfApple.getComment());

	assertEquals(1, productReviewsInMemoryRepository.size());
	assertTrue(productReviewsInMemoryRepository.contains(result));
  }

  @Test
  void givenNoProductReview_whenUpdateProductReview_thenThrowsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var newComment = "New comment about apple";
	var command = new UpdateProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId(),
		newComment
	);

	assertThrows(ProductReviewNotFoundException.class,
		() -> updateProductReviewHandler.handle(command));
  }

  @Test
  void givenOtherUserCommentsOnProduct_whenUpdateProductReviewOfOtherUser_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var newComment = "New comment about apple";
	var command = new UpdateProductReviewCommand(
		AuthMother.admin(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId(),
		newComment
	);

	assertThrows(ForbiddenOperationInProductReviewException.class,
		() -> updateProductReviewHandler.handle(command));
  }

  @Test
  void givenProductReviewOfOtherProduct_whenUpdateProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var newComment = "New comment about apple";
	var pear = ProductMother.pear();
	var command = new UpdateProductReviewCommand(
		AuthMother.userAuth(),
		pear.getId(),
		userReviewOfApple.getId(),
		newComment
	);

	assertThrows(ForbiddenOperationInProductReviewException.class,
		() -> updateProductReviewHandler.handle(command));
  }
}