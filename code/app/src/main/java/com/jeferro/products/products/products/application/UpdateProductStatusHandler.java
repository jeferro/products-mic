package com.jeferro.products.products.products.application;

import static com.jeferro.shared.ddd.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.UpdateProductStatusParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductStatusHandler extends Handler<UpdateProductStatusParams, Product> {

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    public UpdateProductStatusHandler(ProductsRepository productsRepository,
                                EventBus eventBus) {
        super();

        this.productsRepository = productsRepository;
        this.eventBus = eventBus;
    }

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product execute(Context context, UpdateProductStatusParams params) {
        var product = ensureProductExists(params);

        return updateProductStatus(params, product);
    }

    private Product ensureProductExists(UpdateProductStatusParams params) {
        var productCode = params.getProductCode();

	  return productsRepository.findByIdOrError(productCode);
    }

    private Product updateProductStatus(UpdateProductStatusParams params, Product product) {
        var status = params.getStatus();

        product.updateStatus(status);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
