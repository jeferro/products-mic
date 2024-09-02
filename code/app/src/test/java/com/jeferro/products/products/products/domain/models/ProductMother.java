package com.jeferro.products.products.products.domain.models;

import static com.jeferro.products.products.products.domain.models.ProductStatus.PUBLISHED;

import com.jeferro.shared.locale.domain.models.LocalizedData;

public class ProductMother {

  public static Product apple() {
	var productCode = ProductCodeMother.appleCode();
	var name = LocalizedData.createOf(
		"en-US", "Apple",
		"es-ES", "Manzana");

	return new Product(productCode, name, PUBLISHED);
  }

  public static Product pear() {
	var productCode = ProductCodeMother.pearCode();
	var name = LocalizedData.createOf(
		"en-US", "Pear",
		"es-ES", "Pera");

	return new Product(productCode, name, PUBLISHED);
  }

}