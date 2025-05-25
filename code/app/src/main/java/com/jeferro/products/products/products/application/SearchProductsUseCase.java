package com.jeferro.products.products.products.application;

import com.jeferro.products.products.products.application.params.SearchProductsParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.UseCase;
import com.jeferro.shared.ddd.domain.models.aggregates.PaginatedList;
import com.jeferro.shared.ddd.domain.models.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

@Component
@RequiredArgsConstructor
public class SearchProductsUseCase extends UseCase<SearchProductsParams, PaginatedList<Product>> {

    private final ProductsRepository productsRepository;

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public PaginatedList<Product> execute(Context context, SearchProductsParams params) {
        var filter = params.getFilter();

        return productsRepository.findAll(filter);
    }
}
