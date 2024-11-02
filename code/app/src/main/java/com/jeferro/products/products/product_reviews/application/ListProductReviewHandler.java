package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.shared.auth.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.ListProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.ddd.domain.models.context.Context;
import com.jeferro.shared.ddd.application.SilentHandler;
import org.springframework.stereotype.Component;

@Component
public class ListProductReviewHandler extends SilentHandler<ListProductReviewParams, ProductReviews> {

  private final ProductReviewsRepository productReviewsRepository;

  public ListProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  public Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  public ProductReviews execute(Context context, ListProductReviewParams params) {
	var productCode = params.getProductCode();

	return productReviewsRepository.findAllByProductCode(productCode);
  }
}
