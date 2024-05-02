package com.jeferro.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductIdRestMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdRestMapper INSTANCE = new ProductIdRestMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
