package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jeferro.products.product_reviews.application.commands.GetProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.domain.models.ProductMother;
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
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var command = new GetProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId()
	);

	var result = getProductReviewHandler.handle(command);

	assertEquals(userReviewOfApple, result);
  }

  @Test
  void givenNoProductReview_whenGetProductReview_thenThrowsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var command = new GetProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId()
	);

	assertThrows(ProductReviewNotFoundException.class,
		() -> getProductReviewHandler.handle(command));
  }

  @Test
  void givenOtherUserCommentsOnProduct_whenGetProductReviewOfOtherUser_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var command = new GetProductReviewCommand(
		AuthMother.admin(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getId()
	);

	assertThrows(ForbiddenOperationInProductReviewException.class,
		() -> getProductReviewHandler.handle(command));
  }

  @Test
  void givenProductReviewOfOtherProduct_whenGetProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var pear = ProductMother.pear();
	var command = new GetProductReviewCommand(
		AuthMother.userAuth(),
		pear.getId(),
		userReviewOfApple.getId()
	);

	assertThrows(ForbiddenOperationInProductReviewException.class,
		() -> getProductReviewHandler.handle(command));
  }

}