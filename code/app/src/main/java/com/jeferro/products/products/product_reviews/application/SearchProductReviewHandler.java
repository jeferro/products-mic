package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.shared.auth.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.SearchProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.ddd.domain.models.context.Context;
import com.jeferro.shared.ddd.application.SilentHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchProductReviewHandler extends SilentHandler<SearchProductReviewParams, ProductReviews> {

  private final ProductReviewsRepository productReviewsRepository;

  @Override
  public Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  public ProductReviews execute(Context context, SearchProductReviewParams params) {
	var productCode = params.getProductCode();

	return productReviewsRepository.findAllByProductCode(productCode);
  }
}
