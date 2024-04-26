package com.jeferro.products.products.infrastructure.integrations.mongo;


import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.infrastructure.integrations.mongo.daos.ProductsMongoDao;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductMongoMapper;
import com.jeferro.products.shared.infrastructure.mongo.MongoDBContainerCreator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataMongoTest
@Import(ProductsMongoRepository.class)
class ProductsMongoRepositoryIT {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = MongoDBContainerCreator.create();

    private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    private final ProductMongoMapper productMongoMapper = ProductMongoMapper.INSTANCE;

    @Autowired
    private ProductsMongoDao productsMongoDao;

    @Autowired
    private ProductsMongoRepository productsMongoRepository;

    @Nested
    class SaveTests {

        @Test
        void should_saveProduct_when_productDoesNotExist() {
            givenDatabaseIsEmpty();

            var expected = ProductMother.apple();
            productsMongoRepository.save(expected);

            assertProductExistsInDatabase(expected);
        }
    }

    @Nested
    class FindByIdTests {

        @Test
        void should_retrieveProduct_when_productExists() {
            var expected = ProductMother.apple();
            givenProductsInDatabase(expected);

            var result = productsMongoRepository.findById(expected.getId());

            assertTrue(result.isPresent());
            assertEquals(expected, result.get());
        }

        @Test
        void should_retrieveEmpty_when_productDoesNotExist() {
            givenDatabaseIsEmpty();

            var expected = ProductMother.apple();
            var result = productsMongoRepository.findById(expected.getId());

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    class DeleteTests {

        @Test
        void should_deleteProduct_when_productExists() {
            var expected = ProductMother.apple();
            givenProductsInDatabase(expected);

            productsMongoRepository.deleteById(expected.getId());

            assertProductDoesNotExistInDatabase(expected);
        }

        @Test
        void should_deleteProduct_when_productDoesNotExist() {
            givenDatabaseIsEmpty();

            var expected = ProductMother.apple();
            productsMongoRepository.deleteById(expected.getId());

            assertProductDoesNotExistInDatabase(expected);
        }
    }

    @Nested
    class FindAllTests {

        @Test
        void should_retrieveProducts_when_productsExist() {
            var apple = ProductMother.apple();
            var pear = ProductMother.pear();
            givenProductsInDatabase(apple, pear);

            var result = productsMongoRepository.findAll();

            assertEquals(2, result.size());
            assertTrue(result.contains(apple));
            assertTrue(result.contains(pear));
        }

        @Test
        void should_retrieveEmpty_when_productsDoNotExist() {
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