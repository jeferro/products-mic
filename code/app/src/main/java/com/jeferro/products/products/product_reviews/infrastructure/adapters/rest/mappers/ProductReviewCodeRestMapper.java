package com.jeferro.products.products.product_reviews.infrastructure.adapters.rest.mappers;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.products.infrastructure.adapters.rest.mappers.ProductCodeRestMapper;
import com.jeferro.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    UsernameRestMapper.class,
    ProductCodeRestMapper.class
})
public abstract class ProductReviewCodeRestMapper extends IdentifierMapper<ProductReviewId, String> {

  public static final ProductReviewCodeRestMapper INSTANCE = Mappers.getMapper(ProductReviewCodeRestMapper.class);

  private final ProductCodeRestMapper productCodeRestMapper = ProductCodeRestMapper.INSTANCE;

  private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

  public ProductReviewId toDomain(String productCodeDto, String usernameDto) {
    var productCode = productCodeRestMapper.toDomain(productCodeDto);
    var username = usernameRestMapper.toDomain(usernameDto);

    return ProductReviewId.createOf(username, productCode);
  }

}
