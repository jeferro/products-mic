package com.jeferro.products.products.product_reviews.infrastructure.mongo.mappers;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.product_reviews.infrastructure.mongo.dtos.ProductReviewMongoDTO;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.mappers.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class ProductReviewMongoMapper {

  public static final ProductReviewMongoMapper INSTANCE = Mappers.getMapper(ProductReviewMongoMapper.class);

  public String toDTO(ProductReviewId productReviewId) {
    return productReviewId.getUsername() + "::" + productReviewId.getProductCode();
  }

  public ProductReviewId toDomain(String value) {
    var split = value.split("::");

    return toDomain(split[0], split[1]);
  }

  protected abstract ProductReviewId toDomain(String username, String productCode);

  @Mapping( target = "username", ignore = true)
  @Mapping( target = "productCode", ignore = true)
  public abstract ProductReview toDomain(ProductReviewMongoDTO dto);

  public abstract ProductReviewMongoDTO toDTO(ProductReview entity);

  public String toDTO(ProductCode productCode) {
    return productCode.getValue();
  }
}
