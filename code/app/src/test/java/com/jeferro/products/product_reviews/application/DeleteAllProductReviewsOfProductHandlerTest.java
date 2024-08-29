package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.jeferro.products.product_reviews.application.params.DeleteAllProductReviewsOfProductParams;
import com.jeferro.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.domain.models.ProductCodeMother;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.shared.domain.models.auth.UserAuth;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
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
	var now = FakeTimeService.fakesNow();

	givenSeveralAppleReviewsInDatabase();

	var adminAuth = AuthMother.admin();
	var appleId = ProductCodeMother.appleId();

	var params = new DeleteAllProductReviewsOfProductParams(
		appleId
	);

	deleteAllProductReviewsOfProductHandler.handle(adminAuth, params);

	assertThereAreNotReviewsOfApple();

	assertProductReviewDeletedWasPublished(now, adminAuth);
  }

  @Test
  void givenProductDoNotHaveReviews_whenDeleteItsReviews_thenDoNothing() {
	var adminAuth = AuthMother.admin();
	var appleId = ProductCodeMother.appleId();

	var params = new DeleteAllProductReviewsOfProductParams(
		appleId
	);

	deleteAllProductReviewsOfProductHandler.handle(adminAuth, params);

	assertNoEventsWerePublished();
  }

  private void assertThereAreNotReviewsOfApple() {
	assertTrue(productReviewsInMemoryRepository.isEmpty());
  }

  private void assertProductReviewDeletedWasPublished(Instant now, UserAuth adminAuth) {
	Set<ProductReviewId> notifiedProductReviewIds = new HashSet<>();

	eventInMemoryBus.forEach(event -> {
	  assertInstanceOf(ProductReviewDeleted.class, event);

	  assertEquals(now, event.getOccurredOn());
	  assertEquals(adminAuth.who(), event.getOccurredBy());

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