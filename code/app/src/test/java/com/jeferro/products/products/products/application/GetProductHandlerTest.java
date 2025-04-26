package com.jeferro.products.products.products.application;

import com.jeferro.products.products.products.application.params.GetProductParams;
import com.jeferro.products.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductMother;
import com.jeferro.products.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.application.ContextMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        var userContext = ContextMother.user();
        var params = new GetProductParams(
                apple.getId()
        );

        var result = getProductHandler.execute(userContext, params);

        assertEquals(apple, result);
    }

    @Test
    void givenNoProducts_whenGetProduct_thenThrowsException() {
        var apple = ProductMother.apple();

        var userContext = ContextMother.user();
        var params = new GetProductParams(
                apple.getId()
        );

        assertThrows(ProductNotFoundException.class,
                () -> getProductHandler.execute(userContext, params));
    }

    private Product givenAnAppleInDatabase() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);
        return apple;
    }

}