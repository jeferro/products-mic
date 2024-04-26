package com.jeferro.products.products.infrastructure.integrations.mongo.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.integrations.mappers.SimpleIdentifierMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductIdMongoMapper extends SimpleIdentifierMapper<ProductId, String> {

    public static final ProductIdMongoMapper INSTANCE = new ProductIdMongoMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
