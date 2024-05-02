package com.jeferro.products.products.application;

import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.shared.application.SilentHandler;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

public class ListProductsHandler extends SilentHandler<ListProductsCommand, Products> {

    private static final Set<String> MANDATORY_ROLES = Set.of(USER);

    private final ProductsRepository productsRepository;

    public ListProductsHandler(ProductsRepository productsRepository) {
        super(MANDATORY_ROLES);

        this.productsRepository = productsRepository;
    }

    @Override
    public Products handle(ListProductsCommand command) {
        return productsRepository.findAll();
    }
}
