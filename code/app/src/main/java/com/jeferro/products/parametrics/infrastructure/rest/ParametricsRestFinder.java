package com.jeferro.products.parametrics.infrastructure.rest;

import java.util.Optional;
import java.util.Set;

import com.jeferro.products.parametrics.domain.models.ProductType;
import com.jeferro.products.parametrics.domain.models.ProductTypeId;
import com.jeferro.products.parametrics.domain.services.ProductTypeFinder;
import org.springframework.stereotype.Component;

/**
 * TODO: Integrate with other system
 * This class mock integration with other system. This is a anti-corruption layer.
 */
@Component
public class ParametricsRestFinder extends ProductTypeFinder {

  private final Set<ProductType> allowedTypeIds = Set.of(
	  ProductType.createOf(
		  ProductTypeId.createOf("fruit"),
		  "Fruit"),
	  ProductType.createOf(
		  ProductTypeId.createOf("vegetables"),
		  "Vegetables")
  );

  @Override
  public Optional<ProductType> find(ProductTypeId productTypeId) {
		return allowedTypeIds.stream()
			.filter(productType -> productType.hasId(productTypeId))
			.findFirst();
  }
}
