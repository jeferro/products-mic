package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.DeleteProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import com.jeferro.shared.domain.models.auth.Auth;
import org.springframework.stereotype.Component;

@Component
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
  protected ProductReview handle(Auth auth, DeleteProductReviewParams params) {
	var productReview = ensureProductReviewExists(params);

	return deleteProductReview(auth, productReview);
  }

  private ProductReview ensureProductReviewExists(DeleteProductReviewParams params) {
	var productReviewId = params.getProductReviewId();

	return productReviewsRepository.findByIdOrError(productReviewId);
  }

  private ProductReview deleteProductReview(Auth auth, ProductReview productReview) {
	var username = auth.getUsernameOrError();

	productReview.deleteByUser(username);

	productReviewsRepository.deleteById(productReview.getId());

	eventBus.publishAll(productReview);

	return productReview;
  }
}
