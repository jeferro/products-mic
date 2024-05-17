package com.jeferro.products.product_reviews.domain.models;

import java.util.Arrays;
import java.util.List;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.shared.domain.models.aggregates.EntityCollection;

public class ProductReviews extends EntityCollection<ProductReviewId, ProductReview> {

  public ProductReviews(List<ProductReview> entities) {
	super(entities);
  }

  public static Products createOf(Product... products) {
	var entities = Arrays.asList(products);
	return new Products(entities);
  }
}
