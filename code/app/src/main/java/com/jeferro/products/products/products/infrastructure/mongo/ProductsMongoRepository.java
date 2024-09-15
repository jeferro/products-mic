package com.jeferro.products.products.products.infrastructure.mongo;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.infrastructure.mongo.daos.ProductsMongoDao;
import com.jeferro.products.products.products.infrastructure.mongo.dtos.ProductMongoDTO;
import com.jeferro.products.products.products.infrastructure.mongo.mappers.ProductCodeMongoMapper;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.products.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.products.products.infrastructure.mongo.mappers.ProductMongoMapper;
import com.jeferro.products.products.products.infrastructure.mongo.services.ProductCriteriaMongoCreator;
import com.jeferro.shared.auth.infrastructure.adapters.mongo.services.CustomMongoTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductsMongoRepository implements ProductsRepository {

    private final ProductMongoMapper productMongoMapper = ProductMongoMapper.INSTANCE;

    private final ProductCodeMongoMapper productCodeMongoMapper = ProductCodeMongoMapper.INSTANCE;

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
    public Optional<Product> findById(ProductCode productCode) {
        var productCodeDto = productCodeMongoMapper.toDTO(productCode);

        return productsMongoDao.findById(productCodeDto)
                .map(productMongoMapper::toDomain);
    }

    @Override
    public void deleteById(ProductCode productCode) {
        var productCodeDto = productCodeMongoMapper.toDTO(productCode);

        productsMongoDao.deleteById(productCodeDto);
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
