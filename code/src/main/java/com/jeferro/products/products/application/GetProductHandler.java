package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.GetProductCommand;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.services.ProductFetcher;
import com.jeferro.products.shared.application.SilentHandler;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

public class GetProductHandler extends SilentHandler<GetProductCommand, Product> {

    private static final Set<String> MANDATORY_ROLES = Set.of(USER);

    private final ProductFetcher productFetcher;

    public GetProductHandler(ProductFetcher productFetcher) {
        super(MANDATORY_ROLES);

        this.productFetcher = productFetcher;
    }

    @Override
    public Product handle(GetProductCommand command) {
        var productId = command.getProductId();

        return productFetcher.findById(productId);
    }
}
