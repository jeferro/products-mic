package com.jeferro.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(RestProfile.NAME)
public class ProductIdRestMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdRestMapper INSTANCE = new ProductIdRestMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
