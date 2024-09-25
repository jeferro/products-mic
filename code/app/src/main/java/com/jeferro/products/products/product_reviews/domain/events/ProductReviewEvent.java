package com.jeferro.products.products.product_reviews.domain.events;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.auth.domain.models.Username;
import com.jeferro.shared.ddd.domain.events.Event;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;

public abstract class ProductReviewEvent extends Event {

  private ProductReviewId productReviewId;

  protected ProductReviewEvent(EventId id,
	  ProductReviewId productReviewId) {
	super(id);

	setProductReviewId(productReviewId);
  }

  public ProductReviewId getProductReviewId() {
	return productReviewId;
  }

  public ProductCode getProductCode() {
	return productReviewId.getProductCode();
  }

  public Username getUsername() {
	return productReviewId.getUsername();
  }

  private void setProductReviewId(ProductReviewId productReviewId) {
	if (productReviewId == null) {
	  throw ValueValidationException.createOfMessage("Product review identifier is null");
	}

	this.productReviewId = productReviewId;
  }
}
