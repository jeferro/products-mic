package com.jeferro.products.products.infrastructure.adapters.mongo;

import com.jeferro.products.products.infrastructure.adapters.mongo.daos.ProductsMongoDao;
import com.jeferro.products.products.infrastructure.adapters.mongo.dtos.ProductMongoDTO;
import com.jeferro.shared.infrastructure.adapters.mongo.services.auditor.CustomMongoTemplate;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductMongoMapper;
import com.jeferro.products.products.infrastructure.adapters.mongo.services.ProductCriteriaMongoCreator;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductsMongoRepository implements ProductsRepository {

    private final ProductMongoMapper productMongoMapper = ProductMongoMapper.INSTANCE;

    private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    private final ProductsMongoDao productsMongoDao;

    private final ProductCriteriaMongoCreator productCriteriaMongoCreator;

    private final CustomMongoTemplate customMongoTemplate;

    public ProductsMongoRepository(ProductsMongoDao productsMongoDao,
                                   ProductCriteriaMongoCreator productCriteriaMongoCreator,
                                   CustomMongoTemplate customMongoTemplate) {
        this.productsMongoDao = productsMongoDao;
        this.productCriteriaMongoCreator = productCriteriaMongoCreator;
        this.customMongoTemplate = customMongoTemplate;
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
    public Products findAll(ProductCriteria criteria) {
        Query query = productCriteriaMongoCreator.create(criteria);

        Page<ProductMongoDTO> page = customMongoTemplate.findPage(query, ProductMongoDTO.class);

        List<Product> entities = page.getContent().stream()
                .map(productMongoMapper::toDomain)
                .toList();

        return Products.createOfCriteria(entities, criteria, page.getTotalElements());
    }
}
