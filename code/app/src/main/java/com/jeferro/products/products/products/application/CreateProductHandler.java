package com.jeferro.products.products.products.application;

import static com.jeferro.shared.ddd.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.domain.exceptions.ProductAlreadyExistsException;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.products.products.domain.services.ProductTypeFinder;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import org.springframework.stereotype.Component;

@Component
public class CreateProductHandler extends Handler<CreateProductParams, Product> {

    private final ProductsRepository productsRepository;

    private final ProductTypeFinder productTypeFinder;

    private final EventBus eventBus;

    public CreateProductHandler(ProductsRepository productsRepository,
        ProductTypeFinder productTypeFinder,
        EventBus eventBus) {
        super();

        this.productsRepository = productsRepository;
	    this.productTypeFinder = productTypeFinder;
	    this.eventBus = eventBus;
    }

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product execute(Context context, CreateProductParams params) {
        ensureProductDoesNotExist(params);

        return createProduct(params);
    }

    private void ensureProductDoesNotExist(CreateProductParams params) {
        var productCode = params.getCode();

        productsRepository.findById(productCode)
            .ifPresent(product -> { throw ProductAlreadyExistsException.createOf(productCode); });
    }

    private Product createProduct(CreateProductParams params) {
        var code = params.getCode();
        var typeId = params.getTypeId();
        var name = params.getName();

        productTypeFinder.findOrError(typeId);

        var product = Product.create(code, typeId, name);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
