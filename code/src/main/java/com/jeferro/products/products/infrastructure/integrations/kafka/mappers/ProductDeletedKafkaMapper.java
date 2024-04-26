package com.jeferro.products.products.infrastructure.integrations.kafka.mappers;

import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.ProductDeletedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.integrations.mappers.ToDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductDeletedKafkaMapper extends ToDTOMapper<ProductDeleted, ProductDeletedAvroDTO> {

    public static final ProductDeletedKafkaMapper INSTANCE = new ProductDeletedKafkaMapper();

    public final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    @Override
    public ProductDeletedAvroDTO toDTO(ProductDeleted productUpdated) {
        return new ProductDeletedAvroDTO(
                productUpdated.getOccurredOn(),
                productIdMongoMapper.toDTO(productUpdated.getProductId())
        );
    }
}
