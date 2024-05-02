package com.jeferro.products.products.domain.repositories;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.models.Products;

import java.util.Optional;

public interface ProductsRepository {

    void save(Product product);

    Optional<Product> findById(ProductId productId);

    void deleteById(ProductId productId);

    Products findAll();
}
