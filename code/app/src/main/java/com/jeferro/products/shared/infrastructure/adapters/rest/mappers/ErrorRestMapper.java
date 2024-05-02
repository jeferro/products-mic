package com.jeferro.products.shared.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.components.rest.shared.dtos.ErrorRestDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Profile(RestProfile.NAME)
public class ErrorRestMapper {

    public static final ErrorRestMapper INSTANCE = new ErrorRestMapper();

    public ResponseEntity<ErrorRestDTO> toDTO(HttpStatus status, Exception cause) {
        ErrorRestDTO dto = new ErrorRestDTO(cause.getMessage());

        return ResponseEntity
                .status(status)
                .body(dto);
    }
}
