package com.jeferro.products.products.products.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.domain.exceptions.ProductAlreadyExistsException;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import com.jeferro.shared.domain.models.auth.Auth;
import org.springframework.stereotype.Component;

@Component
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
    public Product handle(Auth auth, CreateProductParams params) {
        ensureProductDoesNotExist(params);

        return createProduct(auth, params);
    }

    private void ensureProductDoesNotExist(CreateProductParams params) {
        var productCode = params.getProductCode();

        productsRepository.findById(productCode)
            .ifPresent(product -> { throw ProductAlreadyExistsException.createOf(productCode); });
    }

    private Product createProduct(Auth auth, CreateProductParams params) {
        var productCode = params.getProductCode();
        var name = params.getName();

        var product = Product.create(productCode, name, auth);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
