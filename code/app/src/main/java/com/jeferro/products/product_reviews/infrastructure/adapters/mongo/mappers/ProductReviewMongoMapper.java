package com.jeferro.products.product_reviews.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.product_reviews.infrastructure.adapters.mongo.dtos.ProductReviewMongoDTO;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductCodeMongoMapper;
import com.jeferro.shared.infrastructure.adapters.mongo.mappers.UsernameMongoMapper;
import com.jeferro.shared.infrastructure.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    ProductReviewIdMongoMapper.class,
    UsernameMongoMapper.class,
    ProductCodeMongoMapper.class
})
public abstract class ProductReviewMongoMapper extends BidirectionalMapper<ProductReview, ProductReviewMongoDTO> {

  public static final ProductReviewMongoMapper INSTANCE = Mappers.getMapper(ProductReviewMongoMapper.class);
}
