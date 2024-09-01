package com.jeferro.products.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.DeleteAllProductReviewsOfProductParams;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.products.domain.models.ProductCodeMother;
import com.jeferro.products.shared.application.ContextMother;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteAllProductReviewsOfProductHandlerTest {

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private EventInMemoryBus eventInMemoryBus;

  private DeleteAllProductReviewsOfProductHandler deleteAllProductReviewsOfProductHandler;

  @BeforeEach
  public void beforeEach() {
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();
	eventInMemoryBus = new EventInMemoryBus();

	deleteAllProductReviewsOfProductHandler =
		new DeleteAllProductReviewsOfProductHandler(productReviewsInMemoryRepository, eventInMemoryBus);
  }

  @Test
  void givenSeveralReviewsOfSameProduct_whenDeleteItsReviews_thenPublishEvents() {
	givenSeveralAppleReviewsInDatabase();

	var adminContext = ContextMother.admin();
	var appleCode = ProductCodeMother.appleCode();

	var params = new DeleteAllProductReviewsOfProductParams(
		appleCode
	);

	deleteAllProductReviewsOfProductHandler.execute(adminContext, params);

	assertThereAreNotReviewsOfApple();

	assertProductReviewDeletedWasPublished();
  }

  @Test
  void givenProductDoNotHaveReviews_whenDeleteItsReviews_thenDoNothing() {
	var adminContext = ContextMother.admin();
	var appleCode = ProductCodeMother.appleCode();

	var params = new DeleteAllProductReviewsOfProductParams(
		appleCode
	);

	deleteAllProductReviewsOfProductHandler.execute(adminContext, params);

	assertNoEventsWerePublished();
  }

  private void assertThereAreNotReviewsOfApple() {
	assertTrue(productReviewsInMemoryRepository.isEmpty());
  }

  private void assertProductReviewDeletedWasPublished() {
	Set<ProductReviewId> notifiedProductReviewIds = new HashSet<>();

	eventInMemoryBus.forEach(event -> {
	  assertInstanceOf(ProductReviewDeleted.class, event);

	  ProductReviewDeleted productReviewDeleted = (ProductReviewDeleted) event;
	  notifiedProductReviewIds.add(productReviewDeleted.getProductReviewId());
	});

	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var adminReviewOfApple = ProductReviewMother.adminReviewOfApple();
	Set<ProductReviewId> deletedProductReviewIds = Set.of(userReviewOfApple.getId(),
		adminReviewOfApple.getId());

	assertEquals(deletedProductReviewIds, notifiedProductReviewIds);
  }

  private void assertNoEventsWerePublished() {
	assertTrue(eventInMemoryBus.isEmpty());
  }

  private void givenSeveralAppleReviewsInDatabase() {
	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	var adminReviewOfApple = ProductReviewMother.adminReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple, adminReviewOfApple);
  }

}