package com.jeferro.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import com.jeferro.products.products.domain.events.ProductDeleted;
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
	void givenOneProduct_whenDeleteProduct_thenDeletesProduct() {
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

		assertProductDoesNotExistInDatabase();

		assertEventProductDeleted(apple, userAuth, now);
	}

	@Test
	void givenNoProducts_whenDeleteProduct_thenThrowsProductNotFoundException() {
		var apple = ProductMother.apple();

		var command = new DeleteProductCommand(
			AuthMother.user(),
			apple.getId()
		);

		assertThrows(ProductNotFoundException.class,
			() -> deleteProductHandler.execute(command));
	}

	@Test
	void givenAnonymousAuth_whenDeleteProduct_thenThrowsForbiddenException() {
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
	void givenUserWithoutRoles_whenDeleteProduct_thenThrowsForbiddenException() {
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

	private void assertEventProductDeleted(Product product, Auth auth, Instant now) {
		assertEquals(1, eventInMemoryBus.size());

		var event = (ProductDeleted) eventInMemoryBus.getFirst();

		assertEquals(product.getId(), event.getProductId());
		assertEquals(now, event.getOccurredOn());
		assertEquals(auth.who(), event.getOccurredBy());
	}
}