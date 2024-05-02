package com.jeferro.products.products.infrastructure.adapters.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import com.jeferro.products.components.mongodb.products.ProductsMongoDao;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.mongo.MongoRepositoryIT;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(ProductsMongoRepository.class)
class ProductsMongoRepositoryIT extends MongoRepositoryIT {

	private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

	private final ProductMongoMapper productMongoMapper = ProductMongoMapper.INSTANCE;

	@Autowired
	private ProductsMongoDao productsMongoDao;

	@Autowired
	private ProductsMongoRepository productsMongoRepository;

	@Nested
	class SaveTests {

		@Test
		void givenNoProducts_whenSave_thenSavesProduct() {
			givenDatabaseIsEmpty();

			var expected = ProductMother.apple();
			productsMongoRepository.save(expected);

			assertProductExistsInDatabase(expected);
		}
	}

	@Nested
	class FindByIdTests {

		@Test
		void givenOneProduct_whenFindById_thenReturnsProduct() {
			var expected = ProductMother.apple();
			givenProductsInDatabase(expected);

			var result = productsMongoRepository.findById(expected.getId());

			assertTrue(result.isPresent());
			assertEquals(expected, result.get());
		}

		@Test
		void givenNoProducts_whenFindById_thenReturnsEmpty() {
			givenDatabaseIsEmpty();

			var expected = ProductMother.apple();
			var result = productsMongoRepository.findById(expected.getId());

			assertTrue(result.isEmpty());
		}
	}

	@Nested
	class DeleteTests {

		@Test
		void givenOneProduct_whenDeleteById_thenDeletesProduct() {
			var expected = ProductMother.apple();
			givenProductsInDatabase(expected);

			productsMongoRepository.deleteById(expected.getId());

			assertProductDoesNotExistInDatabase(expected);
		}

		@Test
		void givenNoProducts_whenDeleteById_thenDoNothing() {
			givenDatabaseIsEmpty();

			var expected = ProductMother.apple();
			productsMongoRepository.deleteById(expected.getId());

			assertProductDoesNotExistInDatabase(expected);
		}
	}

	@Nested
	class FindAllTests {

		@Test
		void givenTwoProducts_whenFindAll_thenReturnsAllProducts() {
			var apple = ProductMother.apple();
			var pear = ProductMother.pear();
			givenProductsInDatabase(apple, pear);

			var result = productsMongoRepository.findAll();

			assertEquals(2, result.size());
			assertTrue(result.contains(apple));
			assertTrue(result.contains(pear));
		}

		@Test
		void givenNoProducts_whenFindAll_thenReturnsEmptyList() {
			givenDatabaseIsEmpty();

			var result = productsMongoRepository.findAll();

			assertTrue(result.isEmpty());
		}
	}

	private void givenDatabaseIsEmpty() {
		productsMongoDao.deleteAll();
	}

	private void givenProductsInDatabase(Product... products) {
		productsMongoDao.deleteAll();

		Arrays.stream(products)
			.map(productMongoMapper::toDTO)
			.forEach(productsMongoDao::save);
	}

	private void assertProductExistsInDatabase(Product product) {
		var productIdDto = productIdMongoMapper.toDTO(product.getId());
		var productSaved = productsMongoDao.findById(productIdDto);

		assertTrue(productSaved.isPresent());
	}

	private void assertProductDoesNotExistInDatabase(Product product) {
		var productIdDto = productIdMongoMapper.toDTO(product.getId());
		var productSaved = productsMongoDao.findById(productIdDto);

		assertFalse(productSaved.isPresent());
	}
}