package com.jeferro.products.products.products.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.UpdateProductStatusParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import com.jeferro.shared.domain.models.auth.Auth;
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
    protected Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product handle(Auth auth, UpdateProductStatusParams params) {
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
