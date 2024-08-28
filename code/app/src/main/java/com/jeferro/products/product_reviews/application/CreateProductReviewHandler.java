package com.jeferro.products.product_reviews.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.params.CreateProductReviewParams;
import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewAlreadyExistsException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import com.jeferro.shared.domain.exceptions.ForbiddenException;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.UserAuth;
import com.jeferro.shared.domain.models.auth.Username;

public class CreateProductReviewHandler extends Handler<CreateProductReviewParams, ProductReview> {

  private final ProductsRepository productsRepository;

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public CreateProductReviewHandler(ProductsRepository productsRepository,
	  ProductReviewsRepository productReviewsRepository,
	  EventBus eventBus) {
	super();

	this.productsRepository = productsRepository;
	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(Auth auth, CreateProductReviewParams params) {
	var productId = params.getProductId();
	var comment = params.getComment();

	var username = getAuthUsernameOrError(auth);

	ensureProductExists(productId);

	ensureUserDidNotCommentOnProduct(username, productId);

	return createProductReview(username, productId, comment, auth);
  }

  public Username getAuthUsernameOrError(Auth auth) {
	if (auth instanceof UserAuth userAuth) {
	  return userAuth.getUsername();
	}

	throw ForbiddenException.createOfNotUserAuth(auth);
  }

  private void ensureProductExists(ProductId productId) {
	productsRepository.findByIdOrError(productId);
  }

  private void ensureUserDidNotCommentOnProduct(Username username, ProductId productId) {
	var productReviewId = ProductReviewId.createOf(username, productId);

	productReviewsRepository.findById(productReviewId)
		.ifPresent(current -> { throw ProductReviewAlreadyExistsException.createOf(productReviewId); });
  }

  private ProductReview createProductReview(Username username, ProductId productId, String comment, Auth auth) {
	var productReview = ProductReview.createOf(username, productId, comment, auth);

	productReviewsRepository.save(productReview);

	eventBus.publishAll(productReview);
	return productReview;
  }
}
