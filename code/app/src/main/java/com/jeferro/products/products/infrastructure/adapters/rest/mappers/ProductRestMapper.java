package com.jeferro.products.products.infrastructure.adapters.rest.mappers;

import java.util.List;

import com.jeferro.products.components.rest.generated.dtos.ProductRestDTO;
import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Profile(RestProfile.NAME)
public class ProductRestMapper extends ToDTOMapper<Product, ProductRestDTO> {

    public static final ProductRestMapper INSTANCE = new ProductRestMapper();

    private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

    @Override
    public ProductRestDTO toDTO(Product product) {
        return new ProductRestDTO(
                productIdRestMapper.toDTO(product.getId()),
                product.getName()
        );
    }

    public ResponseEntity<ProductRestDTO> toOkResponseDTO(Product product) {
        var dto = toDTO(product);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<ProductRestDTO>> toOkResponseDTO(Products products) {
        var dtos = products.map(this::toDTO).toList();

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<ProductRestDTO> toCreatedResponseDTO(Product product) {
        var dto = toDTO(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }
}