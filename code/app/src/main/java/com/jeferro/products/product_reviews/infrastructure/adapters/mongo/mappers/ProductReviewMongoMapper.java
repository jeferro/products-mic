package com.jeferro.products.product_reviews.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.product_reviews.dtos.ProductReviewMongoDTO;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.mongo.mappers.UsernameMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    ProductReviewIdMongoMapper.class,
    UsernameMongoMapper.class,
    ProductIdMongoMapper.class
})
public abstract class ProductReviewMongoMapper extends BidirectionalMapper<ProductReview, ProductReviewMongoDTO> {

  public static final ProductReviewMongoMapper INSTANCE = Mappers.getMapper(ProductReviewMongoMapper.class);
}
