package com.jeferro.products.products.infrastructure.integrations.kafka.mappers;

import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.ProductCreatedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.integrations.mappers.ToDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedKafkaMapper extends ToDTOMapper<ProductCreated, ProductCreatedAvroDTO> {

    public static final ProductCreatedKafkaMapper INSTANCE = new ProductCreatedKafkaMapper();

    public final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    @Override
    public ProductCreatedAvroDTO toDTO(ProductCreated productCreated) {
        return new ProductCreatedAvroDTO(
                productCreated.getOccurredOn(),
                productIdMongoMapper.toDTO(productCreated.getProductId())
        );
    }
}
