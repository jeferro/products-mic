package com.jeferro.products.products.products.application;

import static com.jeferro.shared.auth.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.ListProductsParams;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.domain.models.context.Context;
import com.jeferro.shared.ddd.application.SilentHandler;
import org.springframework.stereotype.Component;

@Component
public class ListProductsHandler extends SilentHandler<ListProductsParams, Products> {

    private final ProductsRepository productsRepository;

    public ListProductsHandler(ProductsRepository productsRepository) {
        super();

        this.productsRepository = productsRepository;
    }

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Products execute(Context context, ListProductsParams params) {
        var criteria = params.getCriteria();

        return productsRepository.findAll(criteria);
    }
}
