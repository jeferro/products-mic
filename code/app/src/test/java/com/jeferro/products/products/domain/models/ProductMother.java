package com.jeferro.products.products.domain.models;

public class ProductMother {

  public static Product apple() {
	var productId = ProductIdMother.appleId();

	return new Product(productId, "Apple");
  }

  public static Product pear() {
	var productId = ProductIdMother.pearId();

	return new Product(productId, "Pear");
  }

}