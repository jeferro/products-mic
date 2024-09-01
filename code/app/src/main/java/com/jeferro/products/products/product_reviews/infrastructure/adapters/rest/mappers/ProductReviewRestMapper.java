package com.jeferro.products.products.product_reviews.infrastructure.adapters.rest.mappers;

import java.util.List;

import com.jeferro.products.generated.rest.v1.dtos.ProductReviewRestDTO;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.products.infrastructure.adapters.rest.mappers.ProductCodeRestMapper;
import com.jeferro.shared.auth.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.shared.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;

@Mapper(uses = {
    ProductReviewCodeRestMapper.class,
    UsernameRestMapper.class,
    ProductCodeRestMapper.class
})
public abstract class ProductReviewRestMapper extends ToDTOMapper<ProductReview, ProductReviewRestDTO> {

  public static final ProductReviewRestMapper INSTANCE = Mappers.getMapper(ProductReviewRestMapper.class);

  public ResponseEntity<ProductReviewRestDTO> toOkResponseDTO(ProductReview productReview) {
    var dto = toDTO(productReview);

    return ResponseEntity.ok(dto);
  }

  public ResponseEntity<List<ProductReviewRestDTO>> toOkResponseDTO(ProductReviews productReviews) {
    var dtos = productReviews.map(this::toDTO).toList();

    return ResponseEntity.ok(dtos);
  }
}
