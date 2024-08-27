package com.jeferro.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import com.jeferro.products.products.application.commands.UpdateProductCommand;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
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
        var now = FakeTimeService.fakesNow();

        var apple = givenAnAppleInDatabase();

        var userAuth = AuthMother.user();
        var newProductName = "new product name";
        var command = new UpdateProductCommand(
                userAuth,
                apple.getId(),
                newProductName
        );

        var result = updateProductHandler.execute(command);

        assertEquals(newProductName, result.getName());

        assertProductDataInDatabase(result);

        assertProductUpdatedWasPublished(result, userAuth, now);
    }

    @Test
    void givenNoProducts_whenUpdateProduct_thenThrowsException() {
        var apple = ProductMother.apple();

        var command = new UpdateProductCommand(
                AuthMother.user(),
                apple.getId(),
                "new product name"
        );

        assertThrows(ProductNotFoundException.class,
                () -> updateProductHandler.execute(command));
    }

    private void assertProductDataInDatabase(Product product) {
        assertEquals(1, productsInMemoryRepository.size());

        assertTrue(productsInMemoryRepository.contains(product));
    }

    private void assertProductUpdatedWasPublished(Product product, Auth auth, Instant now) {
        assertEquals(1, eventInMemoryBus.size());

        var event = (ProductUpdated) eventInMemoryBus.getFirstOrError();

        assertEquals(product.getId(), event.getProductId());
		assertEquals(auth.who(), event.getOccurredBy());
		assertEquals(now, event.getOccurredOn());
    }

    private Product givenAnAppleInDatabase() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);
        return apple;
    }
}