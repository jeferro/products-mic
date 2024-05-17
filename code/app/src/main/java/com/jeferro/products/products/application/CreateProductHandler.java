package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.CreateProductCommand;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.events.EventBus;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

public class CreateProductHandler extends Handler<CreateProductCommand, Product> {

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    public CreateProductHandler(ProductsRepository productsRepository, EventBus eventBus) {
        super();

        this.productsRepository = productsRepository;
        this.eventBus = eventBus;
    }

    @Override
    protected Set<String> getMandatoryRoles() {
        return Set.of(USER);
    }

    @Override
    public Product handle(CreateProductCommand command) {
        var auth = command.getAuth();
        var name = command.getName();

        var product = Product.create(name, auth);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
