package com.jeferro.products.products.products.domain.services;

import java.util.Optional;

import com.jeferro.products.products.products.domain.exceptions.ProductTypeNotFoundException;
import com.jeferro.products.products.products.domain.models.product_types.ProductType;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;

public abstract class ProductTypeFinder {

  public ProductType findOrError(ProductTypeId productTypeId) {
	return find(productTypeId)
		.orElseThrow(() -> ProductTypeNotFoundException.createOf(productTypeId));
  }

  protected abstract Optional<ProductType> find(ProductTypeId productTypeId);
}
