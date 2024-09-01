package com.jeferro.products.products.products.application;

import static com.jeferro.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.Context;
import com.jeferro.shared.application.Handler;
import com.jeferro.shared.domain.events.EventBus;
import org.springframework.stereotype.Component;

@Component
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
    public Product handle(Context context, DeleteProductParams params) {
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
