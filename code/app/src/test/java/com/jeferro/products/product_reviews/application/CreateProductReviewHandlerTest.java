package com.jeferro.products.product_reviews.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import com.jeferro.products.product_reviews.application.params.CreateProductReviewParams;
import com.jeferro.products.product_reviews.domain.events.ProductReviewCreated;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewAlreadyExistsException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.products.domain.models.ProductCode;
import com.jeferro.products.products.domain.models.ProductCodeMother;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.shared.domain.models.auth.UserAuth;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateProductReviewHandlerTest {

  private ProductsInMemoryRepository productsInMemoryRepository;

  private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

  private EventInMemoryBus eventInMemoryBus;

  private CreateProductReviewHandler createProductReviewHandler;

  @BeforeEach
  public void beforeEach() {
	productsInMemoryRepository = new ProductsInMemoryRepository();
	productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();
	eventInMemoryBus = new EventInMemoryBus();

	createProductReviewHandler =
		new CreateProductReviewHandler(productsInMemoryRepository, productReviewsInMemoryRepository, eventInMemoryBus);
  }

  @Test
  void givenUserDidNotCommentOnProduct_whenCreateProductReview_thenReturnsNewProductReview() {
	var now = FakeTimeService.fakesNow();

	givenAnAppleInDatabase();

	var userAuth = AuthMother.user();
	var productCode = ProductCodeMother.appleId();
	var comment = "New comment about product";
	var params = new CreateProductReviewParams(
		productCode,
		comment
	);

	var result = createProductReviewHandler.handle(userAuth, params);

	assertResult(userAuth, result, productCode, comment);

	assertProductReviewInDatabase(result);

	assertProductReviewCreatedWasPublished(result, userAuth, now);
  }

  @Test
  void givenUserCommentsOnProduct_whenCreateProductReview_throwsException() {
	var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

	var userAuth = AuthMother.user();
	var params = new CreateProductReviewParams(
		userReviewOfApple.getProductCode(),
		"other comment"
	);

	assertThrows(ProductReviewAlreadyExistsException.class,
		() -> createProductReviewHandler.handle(userAuth, params));
  }

  private static void assertResult(UserAuth userAuth, ProductReview result, ProductCode productCode, String comment) {
	assertEquals(userAuth.getUsername(), result.getUsername());
	assertEquals(productCode, result.getProductCode());
	assertEquals(comment, result.getComment());
  }

  private void assertProductReviewInDatabase(ProductReview result) {
	assertEquals(1, productReviewsInMemoryRepository.size());
	assertTrue(productReviewsInMemoryRepository.contains(result));
  }

  private void assertProductReviewCreatedWasPublished(ProductReview result, UserAuth userAuth, Instant now) {
	assertEquals(1, eventInMemoryBus.size());

	var event = (ProductReviewCreated) eventInMemoryBus.getFirstOrError();

	assertEquals(result.getId(), event.getProductReviewId());
	assertEquals(now, event.getOccurredOn());
	assertEquals(userAuth.who(), event.getOccurredBy());
  }

  private void givenAnAppleInDatabase() {
	var apple = ProductMother.apple();
	productsInMemoryRepository.init(apple);
  }

  private ProductReview givenAnUserProductReviewOfAppleInDatabase() {
	var apple = ProductMother.apple();
	productsInMemoryRepository.init(apple);

	var userReviewOfApple = ProductReviewMother.userReviewOfApple();
	productReviewsInMemoryRepository.init(userReviewOfApple);
	return userReviewOfApple;
  }
}