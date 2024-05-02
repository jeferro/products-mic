package com.jeferro.products.products.application;

import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.products.domain.services.ProductFetcher;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetProductHandlerTest {

    public ProductsInMemoryRepository productsInMemoryRepository;

    public GetProductHandler getProductHandler;

    @BeforeEach
    void beforeEach() {
        productsInMemoryRepository = new ProductsInMemoryRepository();

        var productFetcher = new ProductFetcher(productsInMemoryRepository);

        getProductHandler = new GetProductHandler(productFetcher);
    }

    @Test
    void should_retrieveProduct_when_productExists() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var command = new GetProductCommand(
                AuthMother.user(),
                apple.getId()
        );

        var result = getProductHandler.execute(command);

        assertEquals(apple, result);
    }

    @Test
    void should_throwProductNotFound_when_ProductDoesNotExist() {
        var apple = ProductMother.apple();

        var command = new GetProductCommand(
                AuthMother.user(),
                apple.getId()
        );

        assertThrows(ProductNotFoundException.class,
                () -> getProductHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authIsAnonymous() {
        var apple = ProductMother.apple();

        var command = new GetProductCommand(
                AuthMother.anonymous(),
                apple.getId()
        );

        assertThrows(ForbiddenException.class,
                () -> getProductHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authHasNotTheUserRole() {
        var apple = ProductMother.apple();

        var command = new GetProductCommand(
                AuthMother.userWithoutRoles(),
                apple.getId()
        );

        assertThrows(ForbiddenException.class,
                () -> getProductHandler.execute(command));
    }

}