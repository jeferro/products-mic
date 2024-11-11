package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.GetProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.domain.models.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProductReviewHandler extends Handler<GetProductReviewParams, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  @Override
  public Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  public ProductReview execute(Context context, GetProductReviewParams params) {
	var productReviewId = params.getProductReviewId();

	return productReviewsRepository.findByIdOrError(productReviewId);
  }
}
