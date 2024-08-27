package com.jeferro.products.product_reviews.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.params.DeleteProductReviewParams;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;

public class DeleteProductReviewHandler extends Handler<DeleteProductReviewParams, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public DeleteProductReviewHandler(ProductReviewsRepository productReviewsRepository, EventBus eventBus) {
	super();

	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(DeleteProductReviewParams params) {
	var auth = params.getAuth();
	var productReviewId = params.getProductReviewId();

	var productReview = productReviewsRepository.findByIdOrError(productReviewId);

	productReview.deleteByUser(auth);

	productReviewsRepository.deleteById(productReviewId);

	eventBus.publishAll(productReview);

	return productReview;
  }
}
