package com.jeferro.products.products.application;

import com.jeferro.products.products.application.commands.DeleteProductCommand;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.products.domain.services.ProductFetcher;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteProductHandlerTest {

    public ProductsInMemoryRepository productsInMemoryRepository;

    public EventInMemoryBus eventInMemoryBus;

    public DeleteProductHandler deleteProductHandler;

    @BeforeEach
    void beforeEach() {
        eventInMemoryBus = new EventInMemoryBus();
        productsInMemoryRepository = new ProductsInMemoryRepository();

        var productFetcher = new ProductFetcher(productsInMemoryRepository);

        deleteProductHandler = new DeleteProductHandler(productFetcher, productsInMemoryRepository, eventInMemoryBus);
    }

    @Test
    void should_deleteProduct_when_ProductExists() {
        var now = FakeTimeService.fakesNow();

        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var userAuth = AuthMother.user();
        var command = new DeleteProductCommand(
                userAuth,
                apple.getId()
        );

        var result = deleteProductHandler.execute(command);

        assertEquals(apple, result);
        assertEquals(userAuth.who(), result.getUpdatedBy());
        assertEquals(now, result.getUpdatedAt());

        assertProductDoesNotExistInDatabase();

        assertEventProductDeleted(apple);
    }

    @Test
    void should_throwProductNotFound_when_ProductDoesNotExist() {
        var apple = ProductMother.apple();

        var command = new DeleteProductCommand(
                AuthMother.user(),
                apple.getId()
        );

        assertThrows(ProductNotFoundException.class,
                () -> deleteProductHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authIsAnonymous() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var command = new DeleteProductCommand(
                AuthMother.anonymous(),
                apple.getId()
        );

        assertThrows(ForbiddenException.class,
                () -> deleteProductHandler.execute(command));
    }

    @Test
    void should_throwForbidden_when_authHasNotTheUserRole() {
        var apple = ProductMother.apple();
        productsInMemoryRepository.init(apple);

        var command = new DeleteProductCommand(
                AuthMother.userWithoutRoles(),
                apple.getId()
        );

        assertThrows(ForbiddenException.class,
                () -> deleteProductHandler.execute(command));
    }

    private void assertProductDoesNotExistInDatabase() {
        assertTrue(productsInMemoryRepository.isEmpty());
    }

    private void assertEventProductDeleted(Product product) {
        assertEquals(1, eventInMemoryBus.size());

        var event = (ProductDeleted) eventInMemoryBus.getFirst();

        assertEquals(product.getId(), event.getProductId());
        assertEquals(product.getUpdatedAt(), event.getOccurredOn());
        assertEquals(product.getUpdatedBy(), event.getOccurredBy());
    }
}