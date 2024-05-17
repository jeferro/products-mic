package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.ListProductReviewCommand;
import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.shared.application.Handler;

public class ListProductReviewHandler extends Handler<ListProductReviewCommand, ProductReviews> {

  private final ProductReviewsRepository productReviewsRepository;

  public ListProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReviews handle(ListProductReviewCommand command) {
	var productId = command.getProductId();

	return productReviewsRepository.findAllByProductId(productId);
  }
}
