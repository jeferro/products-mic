package com.jeferro.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jeferro.products.products.application.commands.GetProductCommand;
import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetProductHandlerTest {

    public ProductsInMemoryRepository productsInMemoryRepository;

    public GetProductHandler getProductHandler;

    @BeforeEach
    void beforeEach() {
        productsInMemoryRepository = new ProductsInMemoryRepository();

        getProductHandler = new GetProductHandler(productsInMemoryRepository);
    }

    @Test
    void givenOneProduct_whenGetProduct_thenReturnsProduct() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var command = new GetProductCommand(
                AuthMother.userAuth(),
                apple.getId()
        );

        var result = getProductHandler.execute(command);

        assertEquals(apple, result);
    }

    @Test
    void givenNoProducts_whenGetProduct_thenThrowsProductNotFoundException() {
        var apple = ProductMother.apple();

        var command = new GetProductCommand(
                AuthMother.userAuth(),
                apple.getId()
        );

        assertThrows(ProductNotFoundException.class,
                () -> getProductHandler.execute(command));
    }

    @Test
    void givenAnonymousUser_whenGetProduct_thenThrowsForbiddenException() {
        var apple = ProductMother.apple();

        var command = new GetProductCommand(
                AuthMother.anonymous(),
                apple.getId()
        );

        assertThrows(ForbiddenException.class,
                () -> getProductHandler.execute(command));
    }

    @Test
    void givenUserWithoutUser_whenGetProduct_thenThrowsForbiddenException() {
        var apple = ProductMother.apple();

        var command = new GetProductCommand(
                AuthMother.userWithoutRolesAuth(),
                apple.getId()
        );

        assertThrows(ForbiddenException.class,
                () -> getProductHandler.execute(command));
    }

}