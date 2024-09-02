package com.jeferro.products.products.products.domain.models.product_types;

public class ProductTypeMother {

  public static ProductType fruit() {
	var fruitId = ProductTypeIdMother.fruitId();

	return new ProductType(fruitId, "Fruit");
  }
}
