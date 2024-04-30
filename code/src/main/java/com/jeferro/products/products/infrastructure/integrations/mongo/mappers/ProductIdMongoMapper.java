package com.jeferro.products.products.infrastructure.integrations.mongo.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.integrations.mappers.IdentifierMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductIdMongoMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdMongoMapper INSTANCE = new ProductIdMongoMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
