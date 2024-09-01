package com.jeferro.products.products.products.application;

import static com.jeferro.shared.ddd.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.products.application.params.GetProductParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.SilentHandler;
import org.springframework.stereotype.Component;

@Component
public class GetProductHandler extends SilentHandler<GetProductParams, Product> {

    private final ProductsRepository productsRepository;

    public GetProductHandler(ProductsRepository productsRepository) {
        super();

        this.productsRepository = productsRepository;
    }

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product execute(Context context, GetProductParams params) {
        var productCode = params.getProductCode();

        return productsRepository.findByIdOrError(productCode);
    }
}
