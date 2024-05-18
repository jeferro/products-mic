package com.jeferro.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import com.jeferro.products.products.application.commands.DeleteProductCommand;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.domain.services.time.FakeTimeService;
import org.jetbrains.annotations.NotNull;
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

	deleteProductHandler = new DeleteProductHandler(productsInMemoryRepository, eventInMemoryBus);
  }

  @Test
  void givenOneProduct_whenDeleteProduct_thenDeletesProduct() {
	var now = FakeTimeService.fakesNow();

	var apple = givenAnAppleInDatabase();

	var userAuth = AuthMother.user();
	var command = new DeleteProductCommand(
		userAuth,
		apple.getId()
	);

	var result = deleteProductHandler.execute(command);

	assertEquals(apple, result);

	assertProductDoesNotExistInDatabase();

	assertProductDeletedWasPublished(apple, userAuth, now);
  }

  @Test
  void givenNoProducts_whenDeleteProduct_thenThrowsException() {
	var apple = ProductMother.apple();

	var command = new DeleteProductCommand(
		AuthMother.user(),
		apple.getId()
	);

	assertThrows(ProductNotFoundException.class,
		() -> deleteProductHandler.execute(command));
  }

  @NotNull
  private Product givenAnAppleInDatabase() {
	var apple = ProductMother.apple();
	productsInMemoryRepository.init(apple);
	return apple;
  }

  private void assertProductDoesNotExistInDatabase() {
	assertTrue(productsInMemoryRepository.isEmpty());
  }

  private void assertProductDeletedWasPublished(Product product, Auth auth, Instant now) {
	assertEquals(1, eventInMemoryBus.size());

	var event = (ProductDeleted) eventInMemoryBus.getFirst();

	assertEquals(product.getId(), event.getProductId());
	assertEquals(now, event.getOccurredOn());
	assertEquals(auth.who(), event.getOccurredBy());
  }
}