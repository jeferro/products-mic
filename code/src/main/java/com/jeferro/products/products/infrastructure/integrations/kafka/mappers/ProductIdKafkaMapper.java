package com.jeferro.products.products.infrastructure.integrations.kafka.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.integrations.mappers.IdentifierMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductIdKafkaMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdKafkaMapper INSTANCE = new ProductIdKafkaMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
