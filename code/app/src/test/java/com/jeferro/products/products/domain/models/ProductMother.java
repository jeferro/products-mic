package com.jeferro.products.products.domain.models;

public class ProductMother {

  public static Product apple() {
	var productCode = ProductCodeMother.appleId();

	return new Product(productCode, "Apple");
  }

  public static Product pear() {
	var productCode = ProductCodeMother.pearId();

	return new Product(productCode, "Pear");
  }

}