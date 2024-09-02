package com.jeferro.products.products.products.domain.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.jeferro.products.products.products.domain.models.ProductTypeId;

public class ProductTypeInMemoryChecker extends ProductTypeChecker {

  private final Set<ProductTypeId> allowedTypeIds = new HashSet<>();

  public void reset(ProductTypeId... typeIds) {
	allowedTypeIds.clear();
	allowedTypeIds.addAll(Arrays.asList(typeIds));
  }

  @Override
  protected boolean exists(ProductTypeId productTypeId) {
	return allowedTypeIds.contains(productTypeId);
  }
}
