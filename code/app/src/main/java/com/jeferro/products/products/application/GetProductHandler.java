package com.jeferro.products.products.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.application.commands.GetProductCommand;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.shared.application.SilentHandler;

public class GetProductHandler extends SilentHandler<GetProductCommand, Product> {

    private final ProductsRepository productsRepository;

    public GetProductHandler(ProductsRepository productsRepository) {
        super();

        this.productsRepository = productsRepository;
    }

    @Override
    protected Set<String> getMandatoryRoles() {
        return Set.of(USER);
    }

    @Override
    public Product handle(GetProductCommand command) {
        var productId = command.getProductId();

        return productsRepository.findByIdOrError(productId);
    }
}
