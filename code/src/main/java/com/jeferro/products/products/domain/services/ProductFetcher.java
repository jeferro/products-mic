package com.jeferro.products.products.domain.services;

import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.repositories.ProductsRepository;

public class ProductFetcher {

    private final ProductsRepository productsRepository;

    public ProductFetcher(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product findById(ProductId productId) {
        return productsRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.of(productId));
    }
}
