package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.GetProductReviewCommand;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class GetProductReviewHandler extends Handler<GetProductReviewCommand, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  public GetProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(GetProductReviewCommand command) {
	var auth = command.getAuth();
	var productId = command.getProductId();
	var productReviewId = command.getProductReviewId();

	var productReview = productReviewsRepository.findByIdOrError(productReviewId);

	ensureProductReviewBelongsToProduct(productReview, productId);
	ensureProductReviewBelongsToUserAuth(productReview, auth);

	return productReview;
  }

  private void ensureProductReviewBelongsToProduct(ProductReview productReview, ProductId productId) {
	if (!productReview.belongsToProduct(productId)) {
	  throw ForbiddenOperationInProductReviewException.belongsToOtherProduct(productReview, productId);
	}
  }

  private void ensureProductReviewBelongsToUserAuth(ProductReview productReview, Auth auth) {
	if (!auth.belongsToUser(productReview.getUsername())) {
	  throw ForbiddenOperationInProductReviewException.belongsToOtherUser(productReview, auth);
	}
  }
}
