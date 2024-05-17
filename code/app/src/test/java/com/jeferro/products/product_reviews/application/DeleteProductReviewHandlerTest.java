package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.product_reviews.application.commands.DeleteProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteProductReviewHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private DeleteProductReviewHandler deleteProductReviewHandler;

  @BeforeEach
  public void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();

	deleteProductReviewHandler = new DeleteProductReviewHandler(productReviewsInMemoryRepository);
  }

  @Test
  void givenUserCommentsOnProduct_whenDeleteProductReview_thenReturnsDeletedProductReview() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var command = new DeleteProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId()
	);

	var result = deleteProductReviewHandler.handle(command);

	assertEquals(userReviewOfApple, result);

	assertTrue(productReviewsInMemoryRepository.isEmpty());
  }

  @Test
  void givenUserDoesNotCommentOnProduct_whenDeleteProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var command = new DeleteProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId()
	);

	assertThrows(ProductReviewNotFoundException.class,
		() -> deleteProductReviewHandler.handle(command));
  }

  @Test
  void givenOtherUserCommentsOnProduct_whenDeleteProductReviewOfOtherUser_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var command = new DeleteProductReviewCommand(
		AuthMother.admin(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId()
	);

	assertThrows(ForbiddenOperationInProductReviewException.class,
		() -> deleteProductReviewHandler.handle(command));
  }

  @Test
  void givenProductReviewOfOtherProduct_whenDeleteProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var pear = ProductMother.pear();
	var command = new DeleteProductReviewCommand(
		AuthMother.userAuth(),
		pear.getId(),
		userReviewOfApple.getId()
	);

	assertThrows(ForbiddenOperationInProductReviewException.class,
		() -> deleteProductReviewHandler.handle(command));
  }
}