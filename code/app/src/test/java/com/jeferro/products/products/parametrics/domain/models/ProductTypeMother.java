package com.jeferro.products.products.parametrics.domain.models;

import com.jeferro.products.parametrics.domain.models.ProductType;

public class ProductTypeMother {

  public static ProductType fruit() {
	var fruitId = ProductTypeIdMother.fruitId();

	return new ProductType(fruitId, "Fruit");
  }
}
