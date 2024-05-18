package com.jeferro.products.shared.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.shared.dtos.ErrorRestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Mapper
public class ErrorRestMapper {

    public static final ErrorRestMapper INSTANCE = Mappers.getMapper(ErrorRestMapper.class);

    public ResponseEntity<ErrorRestDTO> toDTO(HttpStatus status, Exception cause) {
        ErrorRestDTO dto = new ErrorRestDTO(cause.getMessage());

        return ResponseEntity
                .status(status)
                .body(dto);
    }
}
