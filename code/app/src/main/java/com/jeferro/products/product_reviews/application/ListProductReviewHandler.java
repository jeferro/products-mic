package com.jeferro.products.product_reviews.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.params.ListProductReviewParams;
import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.application.SilentHandler;
import com.jeferro.shared.domain.models.auth.Auth;

public class ListProductReviewHandler extends SilentHandler<ListProductReviewParams, ProductReviews> {

  private final ProductReviewsRepository productReviewsRepository;

  public ListProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  protected Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReviews handle(Auth auth, ListProductReviewParams params) {
	var productId = params.getProductId();

	return productReviewsRepository.findAllByProductId(productId);
  }
}
