package com.jeferro.products.products.infrastructure.integrations.rest;

import com.jeferro.products.products.application.commands.*;
import com.jeferro.products.products.infrastructure.integrations.rest.dtos.CreateProductRestDTO;
import com.jeferro.products.products.infrastructure.integrations.rest.dtos.ProductRestDTO;
import com.jeferro.products.products.infrastructure.integrations.rest.dtos.UpdateProductRestDTO;
import com.jeferro.products.products.infrastructure.integrations.rest.mappers.ProductIdRestMapper;
import com.jeferro.products.products.infrastructure.integrations.rest.mappers.ProductRestMapper;
import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.integrations.rest.services.AuthJwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/products")
public class ProductsRestController {

    private final ProductRestMapper productRestMapper = ProductRestMapper.INSTANCE;

    private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

    private final AuthJwtService authJwtService;

    private final HandlerBus handlerBus;

    public ProductsRestController(AuthJwtService authJwtService, HandlerBus handlerBus) {
        this.authJwtService = authJwtService;
        this.handlerBus = handlerBus;
    }

    @GetMapping
    public ResponseEntity<List<ProductRestDTO>> list(@RequestHeader(value = AUTHORIZATION, required = false) String authorization) {
        var command = new ListProductsCommand(
                authJwtService.getUserAuth(authorization)
        );

        var products = handlerBus.execute(command);

        return productRestMapper.toOkResponseDTO(products);
    }

    @PostMapping
    public ResponseEntity<ProductRestDTO> create(@RequestHeader(value = AUTHORIZATION, required = false) String authorization,
                                                 @RequestBody CreateProductRestDTO inputDto) {
        var command = new CreateProductCommand(
                authJwtService.getUserAuth(authorization),
                inputDto.name()
        );

        var product = handlerBus.execute(command);

        return productRestMapper.toCreatedResponseDTO(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRestDTO> find(@RequestHeader(value = AUTHORIZATION, required = false) String authorization,
                                               @PathVariable String id) {
        var command = new GetProductCommand(
                authJwtService.getUserAuth(authorization),
                productIdRestMapper.toDomain(id)
        );

        var product = handlerBus.execute(command);

        return productRestMapper.toOkResponseDTO(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRestDTO> update(@RequestHeader(value = AUTHORIZATION, required = false) String authorization,
                                                 @PathVariable String id,
                                                 @RequestBody UpdateProductRestDTO inputDto) {
        var command = new UpdateProductCommand(
                authJwtService.getUserAuth(authorization),
                productIdRestMapper.toDomain(id),
                inputDto.name()
        );

        var user = handlerBus.execute(command);

        return productRestMapper.toOkResponseDTO(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductRestDTO> delete(@RequestHeader(value = AUTHORIZATION, required = false) String authorization,
                                                 @PathVariable String id) {
        var command = new DeleteProductCommand(
                authJwtService.getUserAuth(authorization),
                productIdRestMapper.toDomain(id)
        );

        var user = handlerBus.execute(command);

        return productRestMapper.toOkResponseDTO(user);
    }
}
