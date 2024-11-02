package com.jeferro.products.products.products.application;

import static com.jeferro.shared.auth.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.UpdateProductParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import com.jeferro.shared.ddd.domain.models.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateProductHandler extends Handler<UpdateProductParams, Product> {

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product execute(Context context, UpdateProductParams params) {
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
