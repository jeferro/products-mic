package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.DeleteAllProductReviewsOfProductCommand;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.events.EventBus;

public class DeleteAllProductReviewsOfProductHandler extends Handler<DeleteAllProductReviewsOfProductCommand, Void> {

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public DeleteAllProductReviewsOfProductHandler(ProductReviewsRepository productReviewsRepository, EventBus eventBus) {
	super();

	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected Void handle(DeleteAllProductReviewsOfProductCommand command) {
	var auth = command.getAuth();
	var productId = command.getProductId();

	var productReviews = productReviewsRepository.findAllByProductId(productId);

	if (productReviews.isEmpty()) {
	  return null;
	}

	productReviews.forEach(productReview -> productReview.delete(auth));

	var productReviewIds = productReviews.getIds();

	productReviewsRepository.deleteAllById(productReviewIds);

	eventBus.publishAll(productReviews);

	return null;
  }
}
