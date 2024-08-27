package com.jeferro.products.products.application;

import com.jeferro.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;

import java.util.Set;

import static com.jeferro.shared.application.Roles.USER;

public class CreateProductHandler extends Handler<CreateProductParams, Product> {

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    public CreateProductHandler(ProductsRepository productsRepository, EventBus eventBus) {
        super();

        this.productsRepository = productsRepository;
        this.eventBus = eventBus;
    }

    @Override
    protected Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product handle(CreateProductParams params) {
        var auth = params.getAuth();
        var name = params.getName();

        var product = Product.create(name, auth);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
