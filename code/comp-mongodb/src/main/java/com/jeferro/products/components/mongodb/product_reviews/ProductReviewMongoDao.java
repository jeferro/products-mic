package com.jeferro.products.components.mongodb.product_reviews;

import com.jeferro.products.components.mongodb.product_reviews.dtos.ProductReviewMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewMongoDao extends MongoRepository<ProductReviewMongoDTO, String> {

}
