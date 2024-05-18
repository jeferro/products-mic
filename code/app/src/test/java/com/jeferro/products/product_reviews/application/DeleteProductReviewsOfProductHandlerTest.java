package com.jeferro.products.product_reviews.application;

import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteProductReviewsOfProductHandlerTest {

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

  }

  @Test
  void givenProductDoNotHaveReviews_whenDeleteItsReviews_thenDoNothing() {

  }

}