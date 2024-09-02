package com.jeferro.products.products.product_reviews.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductReviewIdMongoMapper extends IdentifierMapper<ProductReviewId, String> {

  public static final ProductReviewIdMongoMapper INSTANCE = Mappers.getMapper(ProductReviewIdMongoMapper.class);
}
