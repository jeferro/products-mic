package com.jeferro.products.products.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.application.params.UpdateProductParams;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import com.jeferro.shared.domain.models.auth.Auth;

public class UpdateProductHandler extends Handler<UpdateProductParams, Product> {

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    public UpdateProductHandler(ProductsRepository productsRepository,
                                EventBus eventBus) {
        super();

        this.productsRepository = productsRepository;
        this.eventBus = eventBus;
    }

    @Override
    protected Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product handle(Auth auth, UpdateProductParams params) {
        var productId = params.getProductId();
        var name = params.getName();

        var product = productsRepository.findByIdOrError(productId);

        product.update(name, auth);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
