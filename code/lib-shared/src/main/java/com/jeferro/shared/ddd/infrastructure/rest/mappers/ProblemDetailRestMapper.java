package com.jeferro.shared.ddd.infrastructure.rest.mappers;

import com.jeferro.shared.ddd.domain.exceptions.ApplicationException;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@Mapper
public class ProblemDetailRestMapper {

    public static final ProblemDetailRestMapper INSTANCE = Mappers.getMapper(ProblemDetailRestMapper.class);

    public ResponseEntity<ProblemDetail> toDTO(HttpStatus status, Exception cause) {
        var dto = ProblemDetail.forStatus(status);
        dto.setDetail(cause.getMessage());

        if (cause instanceof ApplicationException applicationException) {
            var type = URI.create("data:" + applicationException.getCode());
            dto.setType(type);

            dto.setTitle(applicationException.getTitle());
        }

        return ResponseEntity
                .status(status)
                .body(dto);
    }
}
