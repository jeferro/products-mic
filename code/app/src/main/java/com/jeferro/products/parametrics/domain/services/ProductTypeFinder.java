package com.jeferro.products.parametrics.domain.services;

import java.util.Optional;

import com.jeferro.products.parametrics.domain.exceptions.ProductTypeNotFoundException;
import com.jeferro.products.parametrics.domain.models.ProductType;
import com.jeferro.products.parametrics.domain.models.ProductTypeId;

public abstract class ProductTypeFinder {

  public ProductType findOrError(ProductTypeId productTypeId) {
	return find(productTypeId)
		.orElseThrow(() -> ProductTypeNotFoundException.createOf(productTypeId));
  }

  protected abstract Optional<ProductType> find(ProductTypeId productTypeId);
}
