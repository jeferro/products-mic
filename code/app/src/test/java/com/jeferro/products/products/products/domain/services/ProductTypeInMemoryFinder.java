package com.jeferro.products.products.products.domain.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.jeferro.products.products.products.domain.models.product_types.ProductType;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;

public class ProductTypeInMemoryFinder extends ProductTypeFinder {

  private final Set<ProductType> allowedTypeIds = new HashSet<>();

  public void reset(ProductType... productTypes) {
	allowedTypeIds.clear();
	allowedTypeIds.addAll(Arrays.asList(productTypes));
  }

  @Override
  protected Optional<ProductType> find(ProductTypeId productTypeId) {
	return allowedTypeIds.stream()
		.filter(productType -> productType.hasId(productTypeId))
		.findFirst();
  }
}
