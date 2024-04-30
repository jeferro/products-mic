package com.jeferro.products.products.infrastructure.integrations.mongo;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.products.infrastructure.integrations.mongo.daos.ProductsMongoDao;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductMongoMapper;
import com.jeferro.products.shared.infrastructure.integrations.mongo.services.MetadataMongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductsMongoRepository implements ProductsRepository {

    private final ProductMongoMapper productMongoMapper = ProductMongoMapper.INSTANCE;

    private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

	private final MetadataMongoTemplate metadataMongoTemplate;

    private final ProductsMongoDao productsMongoDao;

    public ProductsMongoRepository(MetadataMongoTemplate metadataMongoTemplate,
		ProductsMongoDao productsMongoDao) {
		this.metadataMongoTemplate = metadataMongoTemplate;
		this.productsMongoDao = productsMongoDao;
    }

    @Override
    public void save(Product product) {
        var dto = productMongoMapper.toDTO(product);

        metadataMongoTemplate.save(dto);
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
