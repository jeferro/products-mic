package com.jeferro.products.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.products.products.application.params.UpdateProductParams;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductMother;
import com.jeferro.products.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateProductHandlerTest {

    private ProductsInMemoryRepository productsInMemoryRepository;

    private EventInMemoryBus eventInMemoryBus;

    private UpdateProductHandler updateProductHandler;

    @BeforeEach
    void beforeEach() {
        eventInMemoryBus = new EventInMemoryBus();
        productsInMemoryRepository = new ProductsInMemoryRepository();

        updateProductHandler = new UpdateProductHandler(productsInMemoryRepository, eventInMemoryBus);
    }

    @Test
    void givenOneProduct_whenUpdateProduct_thenUpdatesProduct() {
        var apple = givenAnAppleInDatabase();

        var userAuth = AuthMother.user();
        var newProductName = "new product name";
        var params = new UpdateProductParams(
                apple.getId(),
                newProductName
        );

        var result = updateProductHandler.execute(userAuth, params);

        assertEquals(newProductName, result.getName());

        assertProductDataInDatabase(result);

        assertProductUpdatedWasPublished(result);
    }

    @Test
    void givenNoProducts_whenUpdateProduct_thenThrowsException() {
        var apple = ProductMother.apple();

        var userAuth = AuthMother.user();
        var params = new UpdateProductParams(
                apple.getId(),
                "new product name"
        );

        assertThrows(ProductNotFoundException.class,
                () -> updateProductHandler.execute(userAuth, params));
    }

    private void assertProductDataInDatabase(Product product) {
        assertEquals(1, productsInMemoryRepository.size());

        assertTrue(productsInMemoryRepository.contains(product));
    }

    private void assertProductUpdatedWasPublished(Product product) {
        assertEquals(1, eventInMemoryBus.size());

        var event = (ProductUpdated) eventInMemoryBus.getFirstOrError();

        assertEquals(product.getId(), event.getCode());
    }

    private Product givenAnAppleInDatabase() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);
        return apple;
    }
}