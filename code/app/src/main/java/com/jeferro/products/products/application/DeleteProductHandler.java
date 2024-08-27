package com.jeferro.products.products.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;

public class DeleteProductHandler extends Handler<DeleteProductParams, Product> {

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    public DeleteProductHandler(ProductsRepository productsRepository,
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
    public Product handle(DeleteProductParams params) {
        var auth = params.getAuth();
        var productId = params.getProductId();

        var product = productsRepository.findByIdOrError(productId);

        product.delete(auth);

        productsRepository.deleteById(productId);

        eventBus.publishAll(product);

        return product;
    }
}
