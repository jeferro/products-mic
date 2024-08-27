package com.jeferro.products.product_reviews.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.params.GetProductReviewParams;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.application.SilentHandler;
import com.jeferro.shared.domain.models.auth.Auth;

public class GetProductReviewHandler extends SilentHandler<GetProductReviewParams, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  public GetProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  protected Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(Auth auth, GetProductReviewParams params) {
	var productReviewId = params.getProductReviewId();

	return productReviewsRepository.findByIdOrError(productReviewId);
  }
}
