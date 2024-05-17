package com.jeferro.products.shared.infrastructure.adapters.rest;

import com.jeferro.products.components.rest.shared.dtos.ErrorRestDTO;
import com.jeferro.products.shared.domain.exceptions.ConstraintException;
import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.exceptions.NotFoundException;
import com.jeferro.products.shared.domain.exceptions.UnauthorizedException;
import com.jeferro.products.shared.infrastructure.adapters.rest.mappers.ErrorRestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ErrorRestController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorRestController.class);

    public static ErrorRestMapper errorRestMapper = ErrorRestMapper.INSTANCE;

    @ResponseBody
    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            ServerWebInputException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<ErrorRestDTO> handleBadRequest(Exception cause) {
        return errorRestMapper.toDTO(HttpStatus.BAD_REQUEST, cause);
    }

    @ResponseBody
    @ExceptionHandler(value = {
            NotFoundException.class,
            NoResourceFoundException.class
    })
    public ResponseEntity<ErrorRestDTO> handleNotFound(Exception cause) {
        return errorRestMapper.toDTO(HttpStatus.NOT_FOUND, cause);
    }

    @ResponseBody
    @ExceptionHandler(value = {
            UnauthorizedException.class
    })
    public ResponseEntity<ErrorRestDTO> handleUnauthorized(Exception cause) {
        return errorRestMapper.toDTO(HttpStatus.UNAUTHORIZED, cause);
    }

    @ResponseBody
    @ExceptionHandler(value = {
            ForbiddenException.class
    })
    public ResponseEntity<ErrorRestDTO> handleForbidden(Exception cause) {
        return errorRestMapper.toDTO(HttpStatus.FORBIDDEN, cause);
    }

    @ResponseBody
    @ExceptionHandler(value = {
            ConstraintException.class
    })
    public ResponseEntity<ErrorRestDTO> handleConstraint(Exception cause) {
        return errorRestMapper.toDTO(HttpStatus.CONFLICT, cause);
    }

    @ResponseBody
    @ExceptionHandler(value = {
            Exception.class
    })
    public ResponseEntity<ErrorRestDTO> handleException(Exception cause) {
        logger.error("Catch an unknown exception", cause);

        return errorRestMapper.toDTO(HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }


}
