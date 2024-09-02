package com.jeferro.products.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCodeMother;
import com.jeferro.products.products.products.domain.models.ProductTypeIdMother;
import com.jeferro.products.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.products.products.domain.services.ProductTypeInMemoryChecker;
import com.jeferro.products.shared.application.ContextMother;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.shared.locale.domain.models.LocalizedData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateProductHandlerTest {

  private ProductsInMemoryRepository productsInMemoryRepository;

  private ProductTypeInMemoryChecker productTypeInMemoryChecker;

  private EventInMemoryBus eventInMemoryBus;

  private CreateProductHandler createProductHandler;

  @BeforeEach
  void beforeEach() {
	eventInMemoryBus = new EventInMemoryBus();
	productsInMemoryRepository = new ProductsInMemoryRepository();

	productTypeInMemoryChecker = new ProductTypeInMemoryChecker();

	createProductHandler = new CreateProductHandler(productsInMemoryRepository,
		productTypeInMemoryChecker,
		eventInMemoryBus);
  }

  @Test
  void givenNoProduct_whenCreateProduct_thenCreatesProduct() {
	var fruitId = ProductTypeIdMother.fruitId();
	productTypeInMemoryChecker.reset(fruitId);

	var userContext = ContextMother.user();
	var code = ProductCodeMother.appleCode();
	var name = LocalizedData.createOf("en-US", "Apple");
	var params = new CreateProductParams(code, fruitId, name);

	var result = createProductHandler.execute(userContext, params);

	assertEquals(name, result.getName());

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