package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.CreateProductCommand;
import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateProductHandlerTest {

    public ProductsInMemoryRepository productsInMemoryRepository;

    public EventInMemoryBus eventInMemoryBus;

    public CreateProductHandler createProductHandler;

    @BeforeEach
    void beforeEach() {
        eventInMemoryBus = new EventInMemoryBus();
        productsInMemoryRepository = new ProductsInMemoryRepository();

        createProductHandler = new CreateProductHandler(productsInMemoryRepository, eventInMemoryBus);
    }

    @Test
    void should_createProduct() {
        var now = FakeTimeService.fakesNow();

        var userAuth = AuthMother.user();
        var productName = "Apple";
        var command = new CreateProductCommand(
                userAuth,
                productName
        );

        var result = createProductHandler.execute(command);

        assertEquals(productName, result.getName());
        assertEquals(now, result.getUpdatedAt());
        assertEquals(userAuth.who(), result.getUpdatedBy());

        assertProductSavedInRepository(result);

        assertEventProductCreated(result);
    }

    @Test
    void should_throwForbidden_when_authIsAnonymous() {
        var productName = "Apple";
        var command = new CreateProductCommand(
                AuthMother.anonymous(),
                productName
        );

        assertThrows(ForbiddenException.class,
                () -> createProductHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authHasNotTheUserRole() {
        var productName = "Apple";
        var command = new CreateProductCommand(
                AuthMother.userWithoutRoles(),
                productName
        );

        assertThrows(ForbiddenException.class,
                () -> createProductHandler.execute(command));
    }

    private void assertProductSavedInRepository(Product product) {
        assertEquals(1, productsInMemoryRepository.size());

        var productSaved = productsInMemoryRepository.getFirst();

        assertEquals(product, productSaved);
    }

    private void assertEventProductCreated(Product product) {
        assertEquals(1, eventInMemoryBus.size());

        var event = (ProductCreated) eventInMemoryBus.getFirst();

        assertEquals(product.getId(), event.getProductId());
        assertEquals(product.getUpdatedAt(), event.getOccurredOn());
        assertEquals(product.getUpdatedBy(), event.getOccurredBy());
    }
}