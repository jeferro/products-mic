package com.jeferro.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.components.mongodb.products.dtos.ProductMongoDTO;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(MongoProfile.NAME)
public class ProductMongoMapper extends BidirectionalMapper<Product, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = new ProductMongoMapper();

    private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    @Override
    public Product toDomain(ProductMongoDTO dto) {
        return new Product(
                productIdMongoMapper.toDomain(dto.id()),
                dto.name()
        );
    }

    @Override
    public ProductMongoDTO toDTO(Product product) {
        return new ProductMongoDTO(
                productIdMongoMapper.toDTO(product.getId()),
                product.getName()
        );
    }
}
