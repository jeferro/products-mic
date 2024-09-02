package com.jeferro.products.products.products.domain.models;

import static com.jeferro.products.products.products.domain.models.ProductStatus.PUBLISHED;

import com.jeferro.products.products.products.domain.models.product_types.ProductTypeIdMother;
import com.jeferro.shared.locale.domain.models.LocalizedData;

public class ProductMother {

  public static Product apple() {
	var productCode = ProductCodeMother.appleCode();
	var fruitId = ProductTypeIdMother.fruitId();
	var name = LocalizedData.createOf(
		"en-US", "Apple",
		"es-ES", "Manzana");

	return new Product(productCode, fruitId, name, PUBLISHED);
  }

  public static Product pear() {
	var productCode = ProductCodeMother.pearCode();
	var fruitId = ProductTypeIdMother.fruitId();
	var name = LocalizedData.createOf(
		"en-US", "Pear",
		"es-ES", "Pera");

	return new Product(productCode, fruitId, name, PUBLISHED);
  }

}