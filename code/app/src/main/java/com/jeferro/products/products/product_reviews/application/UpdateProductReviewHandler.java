package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.shared.ddd.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.UpdateProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
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
  public Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  public ProductReview execute(Context context, UpdateProductReviewParams params) {
	var productReview = ensureProductReviewExists(params);

	return updateProductReview(context, params, productReview);
  }

  private ProductReview ensureProductReviewExists(UpdateProductReviewParams params) {
	var productReviewId = params.getProductReviewId();

	return productReviewsRepository.findByIdOrError(productReviewId);
  }

  private ProductReview updateProductReview(Context context, UpdateProductReviewParams params, ProductReview productReview) {
	var username = context.getUsernameOrError();
	var locale = context.getLocale();

	var comment = params.getComment();

	productReview.update(comment, locale, username);

	productReviewsRepository.save(productReview);

	eventBus.publishAll(productReview);

	return productReview;
  }
}
