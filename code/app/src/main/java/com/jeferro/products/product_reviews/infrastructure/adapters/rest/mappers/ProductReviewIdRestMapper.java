package com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers;

import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductReviewIdRestMapper extends IdentifierMapper<ProductReviewId, String> {

  public static final ProductReviewIdRestMapper INSTANCE = Mappers.getMapper(ProductReviewIdRestMapper.class);

}
