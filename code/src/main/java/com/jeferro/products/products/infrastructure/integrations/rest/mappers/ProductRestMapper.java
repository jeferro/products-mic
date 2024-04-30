package com.jeferro.products.products.infrastructure.integrations.rest.mappers;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.products.infrastructure.integrations.rest.dtos.ProductRestDTO;
import com.jeferro.products.shared.infrastructure.integrations.mappers.ToDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
