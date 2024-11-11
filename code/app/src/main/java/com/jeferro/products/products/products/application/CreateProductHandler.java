package com.jeferro.products.products.products.application;

import static com.jeferro.products.shared.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.parametrics.domain.services.ParametricValidator;
import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import com.jeferro.shared.ddd.domain.models.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductHandler extends Handler<CreateProductParams, Product> {

    private final ProductsRepository productsRepository;

    private final ParametricValidator parametricValidator;

    private final EventBus eventBus;

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(USER);
    }

    @Override
    public Product execute(Context context, CreateProductParams params) {
        var typeId = params.getTypeId();
        var name = params.getName();

        parametricValidator.validateProductType(typeId);

        var code = productsRepository.nextId();

        var product = Product.create(code, typeId, name);

        productsRepository.save(product);

        eventBus.sendAll(product);

        return product;
    }
}
