package com.jeferro.products.product_reviews.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.params.UpdateProductReviewParams;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import com.jeferro.shared.domain.models.auth.Auth;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductReviewHandler extends Handler<UpdateProductReviewParams, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public UpdateProductReviewHandler(ProductReviewsRepository productReviewsRepository, EventBus eventBus) {
	super();

	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  protected Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(Auth auth, UpdateProductReviewParams params) {

	var productReview = ensureProductReviewExists(params);

	return updateProductReview(auth, params, productReview);
  }

  private ProductReview ensureProductReviewExists(UpdateProductReviewParams params) {
	var productReviewId = params.getProductReviewId();

	return productReviewsRepository.findByIdOrError(productReviewId);
  }

  private ProductReview updateProductReview(Auth auth, UpdateProductReviewParams params, ProductReview productReview) {
	var comment = params.getComment();

	productReview.update(comment, auth);

	productReviewsRepository.save(productReview);

	eventBus.publishAll(productReview);

	return productReview;
  }
}
