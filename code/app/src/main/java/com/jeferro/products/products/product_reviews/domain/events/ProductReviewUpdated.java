package com.jeferro.products.products.product_reviews.domain.events;

import java.util.Locale;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import org.apache.commons.lang3.StringUtils;

public class ProductReviewUpdated extends ProductReviewEvent {

  private Locale locale;

  private String comment;

    private ProductReviewUpdated(EventId id,
		ProductReviewId productReviewId,
		Locale locale,
		String comment) {
        super(id, productReviewId);

	  setLocale(locale);
	  setComment(comment);
    }

    public static ProductReviewUpdated create(ProductReview productReview) {
	  var id = EventId.create();

	  var productReviewId = productReview.getId();
	  var locale = productReview.getLocale();
	  var comment = productReview.getComment();

        return new ProductReviewUpdated(id, productReviewId, locale, comment);
    }

  public Locale getLocale() {
	return locale;
  }

  private void setLocale(Locale locale){
	if(locale == null){
	  throw ValueValidationException.createOfMessage("Locale is null");
	}

	this.locale = locale;
  }

  public String getComment() {
	return comment;
  }

  private void setComment(String comment){
	if(StringUtils.isBlank(comment)){
	  throw ValueValidationException.createOfMessage("Comment is blank");
	}

	this.comment = comment;
  }
}
