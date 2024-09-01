package com.jeferro.products.products.products.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.UpdateProductParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Context;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import org.springframework.stereotype.Component;

@Component
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
    public Product handle(Context context, UpdateProductParams params) {
        var product = ensureProductExists(params);

        return updateProduct(params, product);
    }

    private Product ensureProductExists(UpdateProductParams params) {
        var productCode = params.getProductCode();

	  return productsRepository.findByIdOrError(productCode);
    }

    private Product updateProduct(UpdateProductParams params, Product product) {
        var name = params.getName();

        product.update(name);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
