package com.jeferro.products.products.products.infrastructure.adapters.rest.mappers;

import java.util.List;

import com.jeferro.products.generated.rest.v1.dtos.ProductRestDTO;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.shared.locale.infrastructure.adapters.rest.mappers.LocalizedDataRestMapper;
import com.jeferro.shared.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Mapper(uses = {
	ProductCodeRestMapper.class,
    ProductTypeIdRestMapper.class,
    LocalizedDataRestMapper.class
})
public abstract class ProductRestMapper extends ToDTOMapper<Product, ProductRestDTO> {

    public static final ProductRestMapper INSTANCE = Mappers.getMapper(ProductRestMapper.class);

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
