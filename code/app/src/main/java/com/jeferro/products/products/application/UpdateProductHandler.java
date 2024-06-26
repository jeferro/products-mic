package com.jeferro.products.products.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.application.commands.UpdateProductCommand;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.events.EventBus;

public class UpdateProductHandler extends Handler<UpdateProductCommand, Product> {

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
    public Product handle(UpdateProductCommand command) {
        var auth = command.getAuth();
        var productId = command.getProductId();
        var name = command.getName();

        var product = productsRepository.findByIdOrError(productId);

        product.update(name, auth);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
