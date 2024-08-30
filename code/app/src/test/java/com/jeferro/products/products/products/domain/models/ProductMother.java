package com.jeferro.products.products.products.domain.models;

import static com.jeferro.products.products.products.domain.models.ProductStatus.PUBLISHED;

public class ProductMother {

  public static Product apple() {
	var productCode = ProductCodeMother.appleCode();

	return new Product(productCode, "Apple", PUBLISHED);
  }

  public static Product pear() {
	var productCode = ProductCodeMother.pearCode();

	return new Product(productCode, "Pear", PUBLISHED);
  }

}