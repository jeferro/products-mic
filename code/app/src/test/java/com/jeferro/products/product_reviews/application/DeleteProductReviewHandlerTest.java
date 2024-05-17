package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.product_reviews.application.commands.DeleteProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.users.domain.models.UserMother;
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
  void givenACommentOfUser_whenDeleteProductReview_thenReturnsDeletedProductReview() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var command = new DeleteProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getUsername(),
		userReviewOfApple.getProductId()
	);

	var result = deleteProductReviewHandler.handle(command);

	assertEquals(userReviewOfApple, result);

	assertTrue(productReviewsInMemoryRepository.isEmpty());
  }

  @Test
  void givenUserDidNotCommentOnProduct_whenCrateProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var command = new DeleteProductReviewCommand(
		AuthMother.userAuth(),
		userReviewOfApple.getUsername(),
		userReviewOfApple.getProductId()
	);

	assertThrows(ProductReviewNotFoundException.class,
		() -> deleteProductReviewHandler.handle(command));
  }

  @Test
  void givenACommentOfUser_whenDeleteProductReviewOfOtherUser_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var userAuth = AuthMother.userAuth();
	var admin = UserMother.admin();
	var command = new DeleteProductReviewCommand(
		userAuth,
		admin.getUsername(),
		userReviewOfApple.getProductId()
	);

	assertThrows(ForbiddenException.class,
		() -> deleteProductReviewHandler.handle(command));
  }
}