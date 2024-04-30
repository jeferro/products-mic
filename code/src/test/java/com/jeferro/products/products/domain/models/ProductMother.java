package com.jeferro.products.products.domain.models;

public class ProductMother {

    public static Product apple() {
        var productId = new ProductId("apple");

        return new Product(productId, "Apple");
    }

    public static Product pear() {
        var productId = new ProductId("pear");

        return new Product(productId, "Pear");
    }

}