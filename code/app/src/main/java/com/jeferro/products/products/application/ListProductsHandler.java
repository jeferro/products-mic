package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.ListProductsCommand;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.shared.application.SilentHandler;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

public class ListProductsHandler extends SilentHandler<ListProductsCommand, Products> {

    private final ProductsRepository productsRepository;

    public ListProductsHandler(ProductsRepository productsRepository) {
        super();

        this.productsRepository = productsRepository;
    }

    @Override
    protected Set<String> getMandatoryRoles() {
        return Set.of(USER);
    }

    @Override
    public Products handle(ListProductsCommand command) {
        return productsRepository.findAll();
    }
}
