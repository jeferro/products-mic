package com.jeferro.products.products.infrastructure.integrations.kafka.mappers;

import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.ProductUpdatedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.integrations.mappers.ToDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdatedKafkaMapper extends ToDTOMapper<ProductUpdated, ProductUpdatedAvroDTO> {

    public static final ProductUpdatedKafkaMapper INSTANCE = new ProductUpdatedKafkaMapper();

    public final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    @Override
    public ProductUpdatedAvroDTO toDTO(ProductUpdated productUpdated) {
        return new ProductUpdatedAvroDTO(
                productUpdated.getOccurredOn(),
                productIdMongoMapper.toDTO(productUpdated.getProductId())
        );
    }
}
