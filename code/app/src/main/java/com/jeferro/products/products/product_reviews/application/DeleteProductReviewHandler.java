package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.shared.ddd.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.DeleteProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
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
  public Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  public ProductReview execute(Context context, DeleteProductReviewParams params) {
	var productReview = ensureProductReviewExists(params);

	return deleteProductReview(context, productReview);
  }

  private ProductReview ensureProductReviewExists(DeleteProductReviewParams params) {
	var productReviewId = params.getProductReviewId();

	return productReviewsRepository.findByIdOrError(productReviewId);
  }

  private ProductReview deleteProductReview(Context context, ProductReview productReview) {
	var username = context.getUsernameOrError();

	productReview.deleteByUser(username);

	productReviewsRepository.deleteById(productReview.getId());

	eventBus.publishAll(productReview);

	return productReview;
  }
}
