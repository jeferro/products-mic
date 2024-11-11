package com.jeferro.products.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.parametrics.domain.services.ParametricValidator;
import com.jeferro.products.parametrics.infrastructure.parametrics_rest.ParametricRestFinder;
import com.jeferro.products.shared.infrastructure.config.parametrics.ParametricMockRestClient;
import com.jeferro.products.products.parametrics.domain.models.ProductTypeMother;
import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.application.ContextMother;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.shared.locale.domain.models.LocalizedField;
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

	var parametricMockRestClient = new ParametricMockRestClient();
	var parametricFinder = new ParametricRestFinder(parametricMockRestClient);
	var parametricValidator = new ParametricValidator(parametricFinder);

	createProductHandler = new CreateProductHandler(productsInMemoryRepository,
		parametricValidator,
		eventInMemoryBus);
  }

  @Test
  void givenNoProduct_whenCreateProduct_thenCreatesProduct() {
	var fruit = ProductTypeMother.fruit();

	var userContext = ContextMother.user();
	var name = LocalizedField.createOf("en-US", "Apple");
	var params = new CreateProductParams(fruit.getId(), name);

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