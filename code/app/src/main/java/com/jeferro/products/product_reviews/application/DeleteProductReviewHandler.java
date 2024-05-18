package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.DeleteProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.events.EventBus;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class DeleteProductReviewHandler extends Handler<DeleteProductReviewCommand, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public DeleteProductReviewHandler(ProductReviewsRepository productReviewsRepository, EventBus eventBus) {
	super();

	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(DeleteProductReviewCommand command) {
	var auth = command.getAuth();
	var productReviewId = command.getProductReviewId();

	var productReview = productReviewsRepository.findByIdOrError(productReviewId);

	ensureProductReviewBelongsToUserAuth(productReview, auth);

	productReview.delete(auth);

	productReviewsRepository.deleteById(productReviewId);

	eventBus.publishAll(productReview);

	return productReview;
  }

  private void ensureProductReviewBelongsToUserAuth(ProductReview productReview, Auth auth) {
	if (!auth.belongsToUser(productReview.getUsername())) {
	  throw ForbiddenOperationInProductReviewException.belongsToOtherUser(productReview, auth);
	}
  }
}
