package com.jeferro.products.components.mongodb.products;

import com.jeferro.products.components.mongodb.products.dtos.ProductMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductsMongoDao extends MongoRepository<ProductMongoDTO, String> {

}
