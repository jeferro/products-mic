package com.jeferro.products.products.product_reviews.infrastructure.mongo.daos;

import java.util.List;

import com.jeferro.products.products.product_reviews.infrastructure.mongo.dtos.ProductReviewMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewMongoDao extends MongoRepository<ProductReviewMongoDTO, String> {

  List<ProductReviewMongoDTO> findAllByProductCode(String productCode);
}
