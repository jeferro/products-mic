package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.shared.ddd.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.CreateProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewAlreadyExistsException;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import org.springframework.stereotype.Component;

@Component
public class CreateProductReviewHandler extends Handler<CreateProductReviewParams, ProductReview> {

  private final ProductsRepository productsRepository;

  private final ProductReviewsRepository productReviewsRepository;

  private final EventBus eventBus;

  public CreateProductReviewHandler(ProductsRepository productsRepository,
	  ProductReviewsRepository productReviewsRepository,
	  EventBus eventBus) {
	super();

	this.productsRepository = productsRepository;
	this.productReviewsRepository = productReviewsRepository;
	this.eventBus = eventBus;
  }

  @Override
  public Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  public ProductReview execute(Context context, CreateProductReviewParams params) {
	var productCode = ensureProductExists(params);

	ensureProductReviewDoesNotExists(context, productCode);

	return createProductReview(context, params, productCode);
  }

  private ProductCode ensureProductExists(CreateProductReviewParams params) {
	var productCode = params.getProductCode();

	var product = productsRepository.findByIdOrError(productCode);

	return product.getCode();
  }

  private void ensureProductReviewDoesNotExists(Context context, ProductCode productCode) {
	var username = context.getUsernameOrError();

	var productReviewId = ProductReviewId.createOf(username, productCode);

	productReviewsRepository.findById(productReviewId)
		.ifPresent(current -> { throw ProductReviewAlreadyExistsException.createOf(productReviewId); });
  }

  private ProductReview createProductReview(Context context, CreateProductReviewParams params, ProductCode productCode) {
	var username = context.getUsernameOrError();
	var locale = context.getLocale();

	var comment = params.getComment();

	var productReview = ProductReview.createOf(productCode, username, locale, comment);

	productReviewsRepository.save(productReview);

	eventBus.publishAll(productReview);
	return productReview;
  }
}
