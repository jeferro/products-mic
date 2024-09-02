package com.jeferro.products.products.products.domain.services;

import com.jeferro.products.products.products.domain.exceptions.ProductTypeIdNotFoundException;
import com.jeferro.products.products.products.domain.models.ProductTypeId;

public abstract class ProductTypeChecker {

  public void check(ProductTypeId productTypeId) {
	if (!exists(productTypeId)) {
	  throw ProductTypeIdNotFoundException.createOf(productTypeId);
	}
  }

  protected abstract boolean exists(ProductTypeId productTypeId);
}
