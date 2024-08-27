package com.jeferro.products.product_reviews.application;

import static com.jeferro.shared.application.Roles.ADMIN;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.DeleteAllProductReviewsOfProductCommand;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;

public class DeleteAllProductReviewsOfProductHandler extends Handler<DeleteAllProductReviewsOfProductCommand, Void> {

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public DeleteAllProductReviewsOfProductHandler(ProductReviewsRepository productReviewsRepository, EventBus eventBus) {
	super();

	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryUserRoles() {
	return Set.of(ADMIN);
  }

  @Override
  protected Void handle(DeleteAllProductReviewsOfProductCommand command) {
	var auth = command.getAuth();
	var productId = command.getProductId();

	var productReviews = productReviewsRepository.findAllByProductId(productId);

	if (productReviews.isEmpty()) {
	  return null;
	}

	productReviews.forEach(productReview -> productReview.deleteBySystem(auth));

	var productReviewIds = productReviews.getIds();

	productReviewsRepository.deleteAllById(productReviewIds);

	eventBus.publishAll(productReviews);

	return null;
  }
}
