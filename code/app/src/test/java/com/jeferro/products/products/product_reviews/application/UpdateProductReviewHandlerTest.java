package com.jeferro.products.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.products.product_reviews.application.params.UpdateProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewUpdated;
import com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewDoesNotBelongUser;
import com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.application.ContextMother;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateProductReviewHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private EventInMemoryBus eventInMemoryBus;

  private UpdateProductReviewHandler updateProductReviewHandler;

  @BeforeEach
  void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();
	eventInMemoryBus = new EventInMemoryBus();

	updateProductReviewHandler = new UpdateProductReviewHandler(productReviewsInMemoryRepository, eventInMemoryBus);
  }

  @Test
  void givenAProductReview_whenUpdateProductReview_thenReturnsUpdatedProductReview() {
	var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

	var newComment = "New comment about apple";
	var userContext = ContextMother.user();
	var params = new UpdateProductReviewParams(
		userReviewOfApple.getId(),
		newComment
	);
	var result = updateProductReviewHandler.execute(userContext, params);

	assertResult(userReviewOfApple, result, newComment);

	assertProductReviewInDatabase(result);

	assertProductReviewUpdatedWasPublished(result);
  }

  @Test
  void givenNoProductReview_whenUpdateProductReview_thenThrowsException() {
	var userContext = ContextMother.user();
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var newComment = "New comment about apple";
	var params = new UpdateProductReviewParams(
		userReviewOfApple.getId(),
		newComment
	);

	assertThrows(ProductReviewNotFoundException.class,
		() -> updateProductReviewHandler.execute(userContext, params));
  }

  @Test
  void givenOtherUserCommentsOnProduct_whenUpdateProductReviewOfOtherUser_throwsException() {
	var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

	var adminContext = ContextMother.admin();
	var newComment = "New comment about apple";
	var params = new UpdateProductReviewParams(
		userReviewOfApple.getId(),
		newComment
	);

	assertThrows(ProductReviewDoesNotBelongUser.class,
		() -> updateProductReviewHandler.execute(adminContext, params));
  }

  private static void assertResult(ProductReview userReviewOfApple, ProductReview result, String newComment) {
	assertEquals(userReviewOfApple.getId(), result.getId());
	assertEquals(newComment, userReviewOfApple.getComment());
  }

  private void assertProductReviewInDatabase(ProductReview result) {
	assertEquals(1, productReviewsInMemoryRepository.size());
	assertTrue(productReviewsInMemoryRepository.contains(result));
  }

  private void assertProductReviewUpdatedWasPublished(ProductReview result) {
	assertEquals(1, eventInMemoryBus.size());

	var event = (ProductReviewUpdated) eventInMemoryBus.getFirstOrError();

	assertEquals(result.getId(), event.getProductReviewId());
  }

  private ProductReview givenAnUserProductReviewOfAppleInDatabase() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	return userReviewOfApple;
  }
}