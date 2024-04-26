package com.jeferro.products.products.infrastructure.integrations.mongo.mappers;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.infrastructure.integrations.mongo.dtos.ProductMongoDTO;
import com.jeferro.products.shared.infrastructure.integrations.mappers.BidirectionalMapper;
import com.jeferro.products.shared.infrastructure.integrations.mongo.mappers.MetadataMongoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMongoMapper extends BidirectionalMapper<Product, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = new ProductMongoMapper();

    private final MetadataMongoMapper metadataMongoMapper = MetadataMongoMapper.INSTANCE;

    private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    @Override
    public Product toDomain(ProductMongoDTO dto) {
        return new Product(
                productIdMongoMapper.toDomain(dto.id()),
                dto.name(),
                metadataMongoMapper.toDomain(dto.metadata())
        );
    }

    @Override
    public ProductMongoDTO toDTO(Product product) {
        return new ProductMongoDTO(
                productIdMongoMapper.toDTO(product.getId()),
                product.getName(),
                metadataMongoMapper.toDTO(product.getMetadata())
        );
    }
}
