package com.jeferro.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(MongoProfile.NAME)
public class ProductIdMongoMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdMongoMapper INSTANCE = new ProductIdMongoMapper();

    @Override
    public ProductId toDomain(String dto) {
        return new ProductId(dto);
    }
}
