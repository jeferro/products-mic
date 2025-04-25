package com.jeferro.products.products.products.application;

import com.jeferro.products.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import com.jeferro.shared.ddd.domain.models.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

@Component
@RequiredArgsConstructor
public class DeleteProductHandler extends Handler<DeleteProductParams, Product> {

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product execute(Context context, DeleteProductParams params) {
        var product = ensureProductExists(params);

        deleteProduct(product);

        return product;
    }

    private Product ensureProductExists(DeleteProductParams params) {
        var productCode = params.getProductCode();

        return productsRepository.findByIdOrError(productCode);
    }

    private void deleteProduct(Product product) {
        product.delete();

        productsRepository.deleteById(product.getCode());

        eventBus.sendAll(product);
    }
}
