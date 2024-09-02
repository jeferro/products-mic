package com.jeferro.products.products.products.infrastructure.adapters.masters_rest;

import java.util.Set;

import com.jeferro.products.products.products.domain.models.ProductTypeId;
import com.jeferro.products.products.products.domain.services.ProductTypeChecker;
import org.springframework.stereotype.Component;

/**
 * TODO: Integrate with other system
 * This class mock integration with other system. This is a anti-corruption layer.
 */
@Component
public class ProductTypeMastersRestChecker extends ProductTypeChecker {

  private final Set<ProductTypeId> allowedTypeIds = Set.of(
	  ProductTypeId.createOf("fruit"),
	  ProductTypeId.createOf("vegetables")
  );

  @Override
  public boolean exists(ProductTypeId productTypeId) {
		return allowedTypeIds.contains(productTypeId);
  }
}
