package com.jeferro.products.product_reviews.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.generated.dtos.ProductReviewRestDTO;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductIdRestMapper;
import com.jeferro.products.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;

@Mapper(uses = {
    ProductReviewIdRestMapper.class,
    UsernameRestMapper.class,
    ProductIdRestMapper.class
})
public abstract class ProductReviewRestMapper extends BidirectionalMapper<ProductReview, ProductReviewRestDTO> {

  public static final ProductReviewRestMapper INSTANCE = Mappers.getMapper(ProductReviewRestMapper.class);

  public ResponseEntity<ProductReviewRestDTO> toOkResponseDTO(ProductReview productReview) {
    var dto = toDTO(productReview);

    return ResponseEntity.ok(dto);
  }
}
