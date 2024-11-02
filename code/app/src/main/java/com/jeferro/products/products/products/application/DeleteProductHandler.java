package com.jeferro.products.products.products.application;

import static com.jeferro.shared.auth.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.domain.models.context.Context;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

        eventBus.publishAll(product);
    }
}
