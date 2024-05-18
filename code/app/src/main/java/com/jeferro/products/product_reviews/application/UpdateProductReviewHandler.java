package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.UpdateProductReviewCommand;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.events.EventBus;

public class UpdateProductReviewHandler extends Handler<UpdateProductReviewCommand, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public UpdateProductReviewHandler(ProductReviewsRepository productReviewsRepository, EventBus eventBus) {
	super();

	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(UpdateProductReviewCommand command) {
	var auth = command.getAuth();
	var productReviewId = command.getProductReviewId();
	var comment = command.getComment();

	var productReview = productReviewsRepository.findByIdOrError(productReviewId);

	productReview.update(comment, auth);

	productReviewsRepository.save(productReview);

	eventBus.publishAll(productReview);

	return productReview;
  }
}
