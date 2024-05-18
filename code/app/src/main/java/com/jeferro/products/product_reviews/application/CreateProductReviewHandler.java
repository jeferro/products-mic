package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.CreateProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewAlreadyExistsException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.events.EventBus;
import com.jeferro.products.shared.domain.models.users.Username;

public class CreateProductReviewHandler extends Handler<CreateProductReviewCommand, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public CreateProductReviewHandler(ProductReviewsRepository productReviewsRepository, EventBus eventBus) {
	super();

	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(CreateProductReviewCommand command) {
	var auth = command.getAuth();
	var username = command.getAuthUsernameOrError();
	var productId = command.getProductId();
	var comment = command.getComment();

	ensureUserDidNotCommentOnProduct(username, productId);

	var productReview = ProductReview.createOf(username, productId, comment, auth);

	productReviewsRepository.save(productReview);

	eventBus.publishAll(productReview);

	return productReview;
  }

  private void ensureUserDidNotCommentOnProduct(Username username, ProductId productId) {
	var productReviewId = ProductReviewId.createOf(username, productId);

	productReviewsRepository.findById(productReviewId)
		.ifPresent(current -> { throw ProductReviewAlreadyExistsException.createOf(productReviewId); });
  }
}
