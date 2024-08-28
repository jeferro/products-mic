package com.jeferro.products.products.application;

import com.jeferro.products.products.application.params.ListProductsParams;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.application.SilentHandler;
import com.jeferro.shared.domain.models.auth.Auth;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jeferro.shared.application.Roles.USER;

@Component
public class ListProductsHandler extends SilentHandler<ListProductsParams, Products> {

    private final ProductsRepository productsRepository;

    public ListProductsHandler(ProductsRepository productsRepository) {
        super();

        this.productsRepository = productsRepository;
    }

    @Override
    protected Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Products handle(Auth auth, ListProductsParams params) {
        var criteria = params.getProductCriteria();

        return productsRepository.findAll(criteria);
    }
}
