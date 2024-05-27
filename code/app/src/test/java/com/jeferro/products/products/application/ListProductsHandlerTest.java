package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.ListProductsCommand;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListProductsHandlerTest {

    private ProductsInMemoryRepository productsInMemoryRepository;

    private ListProductsHandler listProductsHandler;

    @BeforeEach
    void beforeEach() {
        productsInMemoryRepository = new ProductsInMemoryRepository();

        listProductsHandler = new ListProductsHandler(productsInMemoryRepository);
    }

    @Test
    void givenTwoProducts_whenListProducts_thenReturnsAllProducts() {
        var databaseData = givenSeveralProductsInDatabase();

        var command = new ListProductsCommand(
                AuthMother.user(),
                ProductCriteria.createEmpty()
        );

        var result = listProductsHandler.execute(command);

        assertEquals(2, result.size());
        assertTrue(result.contains(databaseData.apple()));
        assertTrue(result.contains(databaseData.pear()));
    }

    @Test
    void givenTwoProducts_whenListProducts_thenReturnsFilteredProducts() {
        var databaseData = givenSeveralProductsInDatabase();

        var command = new ListProductsCommand(
                AuthMother.user(),
                ProductCriteria.createOfName("pe")
        );

        var result = listProductsHandler.execute(command);

        assertEquals(1, result.size());
        assertFalse(result.contains(databaseData.apple()));
        assertTrue(result.contains(databaseData.pear()));
    }

    @Test
    void givenNoProducts_whenListProduct_thenReturnsEmpty() {
        var command = new ListProductsCommand(
                AuthMother.user(),
                ProductCriteria.createEmpty()
        );

        var result = listProductsHandler.execute(command);

        assertTrue(result.isEmpty());
    }

    private DatabaseData givenSeveralProductsInDatabase() {
        var apple = ProductMother.apple();
        var pear = ProductMother.pear();
        productsInMemoryRepository.init(apple, pear);

        return new DatabaseData(apple, pear);
    }

    private record DatabaseData(Product apple, Product pear) {

    }
}