package com.jeferro.products.products.infrastructure.integrations.rest.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.integrations.mappers.SimpleIdentifierMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductIdRestMapper extends SimpleIdentifierMapper<ProductId, String> {

    public static final ProductIdRestMapper INSTANCE = new ProductIdRestMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
