package com.jeferro.products.products.domain.models;

import com.jeferro.products.shared.domain.models.metadata.MetadataMother;

public class ProductMother {

    public static Product apple() {
        var productId = new ProductId("apple");
		var metadata = MetadataMother.createOfUser();

        return new Product(productId, "Apple", metadata);
    }

    public static Product pear() {
        var productId = new ProductId("pear");
		var metadata = MetadataMother.createOfUser();

        return new Product(productId, "Pear", metadata);
    }

}