package com.jeferro.products.product_reviews.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.product_reviews.application.commands.DeleteProductReviewCommand;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.shared.application.Handler;

public class DeleteProductReviewHandler extends Handler<DeleteProductReviewCommand, ProductReview> {

  private final ProductReviewsRepository productReviewsRepository;

  public DeleteProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReview handle(DeleteProductReviewCommand command) {
	var auth = command.getAuth();
	var productId = command.getProductId();
	var productReviewId = command.getProductReviewId();

	var productReview = productReviewsRepository.findByIdOrError(productReviewId);

	productReview.delete(productId, auth);

	productReviewsRepository.deleteById(productReviewId);

	return productReview;
  }
}
