package com.jeferro.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void givenOneProduct_whenCreateProduct_thenCreatesProduct() {
		var now = FakeTimeService.fakesNow();

		var userAuth = AuthMother.user();
		var productName = "Apple";
		var command = new CreateProductCommand(
			userAuth,
			productName
		);

		var result = createProductHandler.execute(command);

		assertEquals(productName, result.getName());

		assertProductSavedInRepository(result);

		assertEventProductCreated(result, userAuth, now);
	}

	@Test
	void givenAnonymousAuth_whenCreateProduct_thenThrowsForbiddenException() {
		var productName = "Apple";
		var command = new CreateProductCommand(
			AuthMother.anonymous(),
			productName
		);

		assertThrows(ForbiddenException.class,
			() -> createProductHandler.execute(command));
	}

	@Test
	void givenAuthWithoutRoles_whenCreateProduct_thenThrowsForbiddenException() {
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

	private void assertEventProductCreated(Product product, Auth auth, Instant now) {
		assertEquals(1, eventInMemoryBus.size());

		var event = (ProductCreated) eventInMemoryBus.getFirst();

		assertEquals(product.getId(), event.getProductId());
		assertEquals(now, event.getOccurredOn());
		assertEquals(auth.who(), event.getOccurredBy());
	}
}