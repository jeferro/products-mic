package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.UpdateProductCommand;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.products.domain.services.ProductFetcher;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

class UpdateProductHandlerTest {

    public ProductsInMemoryRepository productsInMemoryRepository;

    public EventInMemoryBus eventInMemoryBus;

    public UpdateProductHandler updateProductHandler;

    @BeforeEach
    void beforeEach() {
        eventInMemoryBus = new EventInMemoryBus();
        productsInMemoryRepository = new ProductsInMemoryRepository();

        var productFetcher = new ProductFetcher(productsInMemoryRepository);

        updateProductHandler = new UpdateProductHandler(productFetcher, productsInMemoryRepository, eventInMemoryBus);
    }

    @Test
    void should_updateProduct_when_productExists() {
        var now = FakeTimeService.fakesNow();

        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var productName = "new product name";
        var userAuth = AuthMother.user();
        var command = new UpdateProductCommand(
                userAuth,
                apple.getId(),
                productName
        );

        var result = updateProductHandler.execute(command);

        assertEquals(productName, result.getName());

        assertProductUpdatedInRepository(result);

        assertEventProductUpdated(result, userAuth, now);
    }

    @Test
    void should_throwProductNotFound_when_productDoesNotExist() {
        var apple = ProductMother.apple();

        var command = new UpdateProductCommand(
                AuthMother.user(),
                apple.getId(),
                "new product name"
        );

        assertThrows(ProductNotFoundException.class,
                () -> updateProductHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authIsAnonymous() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var command = new UpdateProductCommand(
                AuthMother.anonymous(),
                apple.getId(),
                "new product name"
        );

        assertThrows(ForbiddenException.class,
                () -> updateProductHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authHasNotTheUserRole() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var command = new UpdateProductCommand(
                AuthMother.userWithoutRoles(),
                apple.getId(),
                "new product name"
        );

        assertThrows(ForbiddenException.class,
                () -> updateProductHandler.execute(command));
    }

    private void assertProductUpdatedInRepository(Product product) {
        assertEquals(1, productsInMemoryRepository.size());

        var productSaved = productsInMemoryRepository.getFirst();

        assertEquals(product, productSaved);
    }

    private void assertEventProductUpdated(Product product, Auth auth, Instant now) {
        assertEquals(1, eventInMemoryBus.size());

        var event = (ProductUpdated) eventInMemoryBus.getFirst();

        assertEquals(product.getId(), event.getProductId());
		assertEquals(auth.who(), event.getOccurredBy());
		assertEquals(now, event.getOccurredOn());
    }
}