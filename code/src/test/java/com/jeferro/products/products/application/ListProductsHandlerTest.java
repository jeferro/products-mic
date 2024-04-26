package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.ListProductsCommand;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListProductsHandlerTest {

    public ProductsInMemoryRepository productsInMemoryRepository;

    public ListProductsHandler listProductsHandler;

    @BeforeEach
    void beforeEach() {
        productsInMemoryRepository = new ProductsInMemoryRepository();

        listProductsHandler = new ListProductsHandler(productsInMemoryRepository);
    }

    @Test
    void should_retrieveProducts_when_productsExist() {
        var apple = ProductMother.apple();
        var pear = ProductMother.pear();
        productsInMemoryRepository.init(apple, pear);

        var command = new ListProductsCommand(
                AuthMother.user()
        );

        var result = listProductsHandler.execute(command);

        assertEquals(2, result.size());
        assertTrue(result.contains(apple));
        assertTrue(result.contains(pear));
    }

    @Test
    void should_retrieveEmpty_when_productsDoNotExist() {
        var command = new ListProductsCommand(
                AuthMother.user()
        );

        var result = listProductsHandler.execute(command);

        assertTrue(result.isEmpty());
    }

    @Test
    void should_throwForbidden_when_authIsAnonymous() {
        var apple = ProductMother.apple();
        var pear = ProductMother.pear();
        productsInMemoryRepository.init(apple, pear);

        var command = new ListProductsCommand(
                AuthMother.anonymous()
        );

        assertThrows(ForbiddenException.class,
                () -> listProductsHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authHasNotTheUserRole() {
        var apple = ProductMother.apple();
        var pear = ProductMother.pear();
        productsInMemoryRepository.init(apple, pear);

        var command = new ListProductsCommand(
                AuthMother.userWithoutRoles()
        );

        assertThrows(ForbiddenException.class,
                () -> listProductsHandler.execute(command));
    }

}