package com.jeferro.products.products.infrastructure.integrations.kafka.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.integrations.mappers.SimpleIdentifierMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductIdKafkaMapper extends SimpleIdentifierMapper<ProductId, String> {

    public static final ProductIdKafkaMapper INSTANCE = new ProductIdKafkaMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
