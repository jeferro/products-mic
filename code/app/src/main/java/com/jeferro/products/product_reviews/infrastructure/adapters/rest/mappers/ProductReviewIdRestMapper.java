package com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers;

import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductIdRestMapper;
import com.jeferro.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    UsernameRestMapper.class,
    ProductIdRestMapper.class
})
public abstract class ProductReviewIdRestMapper extends IdentifierMapper<ProductReviewId, String> {

  public static final ProductReviewIdRestMapper INSTANCE = Mappers.getMapper(ProductReviewIdRestMapper.class);

  private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

  private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

  public ProductReviewId toDomain(String productIdDto, String usernameDto) {
    var productId = productIdRestMapper.toDomain(productIdDto);
    var username = usernameRestMapper.toDomain(usernameDto);

    return ProductReviewId.createOf(username, productId);
  }

}
