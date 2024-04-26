package com.jeferro.products.users.infrastructure.integrations.rest;

import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.integrations.rest.mappers.UsernameRestMapper;
import com.jeferro.products.shared.infrastructure.integrations.rest.services.AuthJwtService;
import com.jeferro.products.users.application.commands.SignInCommand;
import com.jeferro.products.users.infrastructure.integrations.rest.dtos.SignInRestDTO;
import com.jeferro.products.users.infrastructure.integrations.rest.dtos.UserRestDTO;
import com.jeferro.products.users.infrastructure.integrations.rest.mappers.UserRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

// TODO: Apply API FIRST including jwt auth
@RestController
@RequestMapping("api/v1/authentications")
public class AuthenticationsRestController {

    private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

    private final UserRestMapper userRestMapper = UserRestMapper.INSTANCE;

    private final AuthJwtService authJwtService;

    private final HandlerBus handlerBus;

    public AuthenticationsRestController(AuthJwtService authJwtService, HandlerBus handlerBus) {
        this.authJwtService = authJwtService;
        this.handlerBus = handlerBus;
    }

    @PostMapping
    public ResponseEntity<UserRestDTO> create(@RequestHeader(value = AUTHORIZATION, required = false) String authorization,
                                              @RequestBody SignInRestDTO signInDto) {
        var command = new SignInCommand(
                authJwtService.getUserAuth(authorization),
                usernameRestMapper.toDomain(signInDto.username()),
                signInDto.password()
        );

        var user = handlerBus.execute(command);

        var jwtHeader = authJwtService.calculateJwtHeader(signInDto.username(), user.getRoles());

        return ResponseEntity.ok()
                .header(AUTHORIZATION, jwtHeader)
                .body(userRestMapper.toDTO(user));
    }
}
