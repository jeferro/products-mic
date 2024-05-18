package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.GetProductReviewCommand;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.shared.application.SilentHandler;

public class GetProductReviewHandler extends SilentHandler<GetProductReviewCommand, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  public GetProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(GetProductReviewCommand command) {
	var productReviewId = command.getProductReviewId();

	return productReviewsRepository.findByIdOrError(productReviewId);
  }
}
