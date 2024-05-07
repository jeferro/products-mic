package com.jeferro.products.products.infrastructure.adapters.mongo;

import java.util.Optional;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.components.mongodb.products.ProductsMongoDao;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductMongoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(MongoProfile.NAME)
public class ProductsMongoRepository implements ProductsRepository {

    private final ProductMongoMapper productMongoMapper = ProductMongoMapper.INSTANCE;

    private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    private final ProductsMongoDao productsMongoDao;

    public ProductsMongoRepository(ProductsMongoDao productsMongoDao) {
		this.productsMongoDao = productsMongoDao;
    }

    @Override
    public void save(Product product) {
        var dto = productMongoMapper.toDTO(product);

        productsMongoDao.save(dto);
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        var productIdDto = productIdMongoMapper.toDTO(productId);

        return productsMongoDao.findById(productIdDto)
                .map(productMongoMapper::toDomain);
    }

    @Override
    public void deleteById(ProductId productId) {
        var productIdDto = productIdMongoMapper.toDTO(productId);

        productsMongoDao.deleteById(productIdDto);
    }

    @Override
    public Products findAll() {
        var products = productsMongoDao.findAll().stream()
                .map(productMongoMapper::toDomain)
                .toList();

        return Products.createOf(products);
    }
}
