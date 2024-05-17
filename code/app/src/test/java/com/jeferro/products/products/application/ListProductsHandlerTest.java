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
    void givenTwoProducts_whenListProducts_thenReturnsAllProducts() {
        var apple = ProductMother.apple();
        var pear = ProductMother.pear();
        productsInMemoryRepository.init(apple, pear);

        var command = new ListProductsCommand(
                AuthMother.userAuth()
        );

        var result = listProductsHandler.execute(command);

        assertEquals(2, result.size());
        assertTrue(result.contains(apple));
        assertTrue(result.contains(pear));
    }

    @Test
    void givenNoProducts_whenListProduct_thenReturnsEmpty() {
        var command = new ListProductsCommand(
                AuthMother.userAuth()
        );

        var result = listProductsHandler.execute(command);

        assertTrue(result.isEmpty());
    }

    @Test
    void givenAnonymousUser_whenListProducts_thenThrowsForbiddenException() {
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
    void givenUserWithoutRoles_whenListProducts_thenThrowsForbiddenException() {
        var apple = ProductMother.apple();
        var pear = ProductMother.pear();
        productsInMemoryRepository.init(apple, pear);

        var command = new ListProductsCommand(
                AuthMother.userWithoutRolesAuth()
        );

        assertThrows(ForbiddenException.class,
                () -> listProductsHandler.execute(command));
    }

}