package com.jeferro.products.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCodeMother;
import com.jeferro.products.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.shared.domain.models.locale.LocalizedData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateProductHandlerTest {

  private ProductsInMemoryRepository productsInMemoryRepository;

  private EventInMemoryBus eventInMemoryBus;

  private CreateProductHandler createProductHandler;

  @BeforeEach
  void beforeEach() {
	eventInMemoryBus = new EventInMemoryBus();
	productsInMemoryRepository = new ProductsInMemoryRepository();

	createProductHandler = new CreateProductHandler(productsInMemoryRepository, eventInMemoryBus);
  }

  @Test
  void givenNoProduct_whenCreateProduct_thenCreatesProduct() {
	var userAuth = AuthMother.user();
	var productCode = ProductCodeMother.appleCode();
	var productName = LocalizedData.createOf("en-US", "Apple");
	var params = new CreateProductParams(productCode, productName);

	var result = createProductHandler.execute(userAuth, params);

	assertEquals(productName, result.getName());

	assertProductDataInDatabase(result);

	assertProductCreatedWasPublished(result);
  }

  private void assertProductDataInDatabase(Product result) {
	assertEquals(1, productsInMemoryRepository.size());
	assertTrue(productsInMemoryRepository.contains(result));
  }

  private void assertProductCreatedWasPublished(Product result) {
	assertEquals(1, eventInMemoryBus.size());

	var event = (ProductCreated) eventInMemoryBus.getFirstOrError();

	assertEquals(result.getId(), event.getCode());
  }
}