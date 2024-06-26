package com.jeferro.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.products.application.commands.GetProductCommand;
import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetProductHandlerTest {

    private ProductsInMemoryRepository productsInMemoryRepository;

    private GetProductHandler getProductHandler;

    @BeforeEach
    void beforeEach() {
        productsInMemoryRepository = new ProductsInMemoryRepository();

        getProductHandler = new GetProductHandler(productsInMemoryRepository);
    }

    @Test
    void givenOneProduct_whenGetProduct_thenReturnsProduct() {
        var apple = givenAnAppleInDatabase();

        var command = new GetProductCommand(
                AuthMother.user(),
                apple.getId()
        );

        var result = getProductHandler.execute(command);

        assertEquals(apple, result);
    }

    @Test
    void givenNoProducts_whenGetProduct_thenThrowsException() {
        var apple = ProductMother.apple();

        var command = new GetProductCommand(
                AuthMother.user(),
                apple.getId()
        );

        assertThrows(ProductNotFoundException.class,
                () -> getProductHandler.execute(command));
    }

    private Product givenAnAppleInDatabase() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);
        return apple;
    }

}