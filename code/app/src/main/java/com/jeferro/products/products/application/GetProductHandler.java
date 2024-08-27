package com.jeferro.products.products.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.application.params.GetProductParams;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.SilentHandler;

public class GetProductHandler extends SilentHandler<GetProductParams, Product> {

    private final ProductsRepository productsRepository;

    public GetProductHandler(ProductsRepository productsRepository) {
        super();

        this.productsRepository = productsRepository;
    }

    @Override
    protected Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product handle(GetProductParams params) {
        var productId = params.getProductId();

        return productsRepository.findByIdOrError(productId);
    }
}
