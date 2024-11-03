package com.jeferro.products.products.parametrics.infrastructure.in_memory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.jeferro.products.parametrics.domain.models.ProductType;
import com.jeferro.products.parametrics.domain.models.ProductTypeId;
import com.jeferro.products.parametrics.domain.services.ProductTypeFinder;

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
