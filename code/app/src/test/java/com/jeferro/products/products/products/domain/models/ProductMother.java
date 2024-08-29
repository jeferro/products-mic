package com.jeferro.products.products.products.domain.models;

public class ProductMother {

  public static Product apple() {
	var productCode = ProductCodeMother.appleCode();

	return new Product(productCode, "Apple");
  }

  public static Product pear() {
	var productCode = ProductCodeMother.pearCode();

	return new Product(productCode, "Pear");
  }

}