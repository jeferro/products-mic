package com.jeferro.products.products.product_reviews.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductReviewIdKafkaMapper extends IdentifierMapper<ProductReviewId, String> {

	public static final ProductReviewIdKafkaMapper INSTANCE = Mappers.getMapper(ProductReviewIdKafkaMapper.class);

}
