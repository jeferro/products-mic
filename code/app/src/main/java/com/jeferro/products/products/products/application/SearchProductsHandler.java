package com.jeferro.products.products.products.application;

import static com.jeferro.shared.auth.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.SearchProductsParams;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.domain.models.context.Context;
import com.jeferro.shared.ddd.application.SilentHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchProductsHandler extends SilentHandler<SearchProductsParams, Products> {

    private final ProductsRepository productsRepository;

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Products execute(Context context, SearchProductsParams params) {
        var criteria = params.getCriteria();

        return productsRepository.findAll(criteria);
    }
}
