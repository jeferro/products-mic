package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jeferro.products.product_reviews.application.commands.CreateProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewAlreadyExistsException;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.domain.models.ProductIdMother;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.users.domain.models.UserMother;
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
  void givenUserDidNotCommentOnProduct_whenCrateProductReview_thenReturnsNewProductReview() {
	var userAuth = AuthMother.userAuth();
	var productId = ProductIdMother.appleId();
	var comment = "New comment about product";

	var command = new CreateProductReviewCommand(
		userAuth,
		userAuth.getUsername(),
		productId,
		comment
	);

	var result = createProductReviewHandler.handle(command);

	assertEquals(userAuth.getUsername(), result.getUsername());
	assertEquals(productId, result.getProductId());
	assertEquals(comment, result.getComment());
  }

  @Test
  void givenUserDidNotCommentOnProduct_whenCrateProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	var userAuth = AuthMother.userAuth();

	var command = new CreateProductReviewCommand(
		AuthMother.userAuth(),
		userAuth.getUsername(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getComment()
	);

	assertThrows(ProductReviewAlreadyExistsException.class,
		() -> createProductReviewHandler.handle(command));
  }

  @Test
  void givenNoData_whenCrateProductReviewOfOtherUser_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);

	var userAuth = AuthMother.userAuth();
	var admin = UserMother.admin();
	var command = new CreateProductReviewCommand(
		userAuth,
		admin.getUsername(),
		userReviewOfApple.getProductId(),
		userReviewOfApple.getComment()
	);

	assertThrows(ForbiddenException.class,
		() -> createProductReviewHandler.handle(command));
  }
}