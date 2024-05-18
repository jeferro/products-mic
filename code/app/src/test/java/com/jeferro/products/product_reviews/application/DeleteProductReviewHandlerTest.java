package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import com.jeferro.products.product_reviews.application.commands.DeleteProductReviewCommand;
import com.jeferro.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.domain.models.auth.UserAuth;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteProductReviewHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private EventInMemoryBus eventInMemoryBus;

  private DeleteProductReviewHandler deleteProductReviewHandler;

  @BeforeEach
  public void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();

	eventInMemoryBus = new EventInMemoryBus();

	deleteProductReviewHandler = new DeleteProductReviewHandler(productReviewsInMemoryRepository, eventInMemoryBus);
  }

  @Test
  void givenUserCommentsOnProduct_whenDeleteProductReview_thenReturnsDeletedProductReview() {
	var now = FakeTimeService.fakesNow();

	var userReviewOfApple = givenAnUserProductReviewOfApple();

	var userAuth = AuthMother.user();
	var command = new DeleteProductReviewCommand(
		userAuth,
		userReviewOfApple.getId()
	);

	var result = deleteProductReviewHandler.handle(command);

	assertEquals(userReviewOfApple, result);

	assertTrue(productReviewsInMemoryRepository.isEmpty());

	assertProductReviewDeletedWasPublished(result, userAuth, now);
  }

  @Test
  void givenUserDoesNotCommentOnProduct_whenDeleteProductReview_throwsException() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var command = new DeleteProductReviewCommand(
		AuthMother.user(),
		userReviewOfApple.getId()
	);

	assertThrows(ProductReviewNotFoundException.class,
		() -> deleteProductReviewHandler.handle(command));
  }

  @Test
  void givenOtherUserCommentsOnProduct_whenDeleteProductReviewOfOtherUser_throwsException() {
	var userReviewOfApple = givenAnUserProductReviewOfApple();

	var command = new DeleteProductReviewCommand(
		AuthMother.admin(),
		userReviewOfApple.getId()
	);

	assertThrows(ForbiddenOperationInProductReviewException.class,
		() -> deleteProductReviewHandler.handle(command));
  }

  @Test
  void handlerShouldDoOperationUsers() {
	var mandatoryRoles = deleteProductReviewHandler.getMandatoryRoles();

	assertEquals(1, mandatoryRoles.size());
	assertTrue(mandatoryRoles.contains("user"));
  }

  private void assertProductReviewDeletedWasPublished(ProductReview result, UserAuth userAuth, Instant now) {
	assertEquals(1, eventInMemoryBus.size());

	var event = (ProductReviewDeleted) eventInMemoryBus.getFirstOrError();

	assertEquals(result.getId(), event.getProductReviewId());
	assertEquals(now, event.getOccurredOn());
	assertEquals(userAuth.who(), event.getOccurredBy());
  }

  private ProductReview givenAnUserProductReviewOfApple() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	return userReviewOfApple;
  }
}