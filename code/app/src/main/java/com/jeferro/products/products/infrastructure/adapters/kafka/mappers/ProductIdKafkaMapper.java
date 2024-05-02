package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(KafkaProfile.NAME)
public class ProductIdKafkaMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdKafkaMapper INSTANCE = new ProductIdKafkaMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
