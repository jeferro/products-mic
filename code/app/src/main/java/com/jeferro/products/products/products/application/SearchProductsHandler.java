package com.jeferro.products.products.products.application;

import com.jeferro.products.products.products.application.params.SearchProductsParams;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.domain.models.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

@Component
@RequiredArgsConstructor
public class SearchProductsHandler extends Handler<SearchProductsParams, Products> {

    private final ProductsRepository productsRepository;

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Products execute(Context context, SearchProductsParams params) {
        var filter = params.getFilter();

        return productsRepository.findAll(filter);
    }
}
