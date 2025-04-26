package com.jeferro.products.users.users.infrastructure.rest;

import com.jeferro.products.users.users.infrastructure.rest.dtos.AuthRestDTO;
import com.jeferro.products.users.users.infrastructure.rest.dtos.SignInInputRestDTO;
import com.jeferro.products.users.users.infrastructure.rest.mappers.AuthRestMapper;
import com.jeferro.shared.auth.infrastructure.rest.jwt.HeaderJwtDecoder;
import com.jeferro.shared.ddd.application.bus.HandlerBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
public class AuthenticationsRestController {

    private final AuthRestMapper authRestMapper = AuthRestMapper.INSTANCE;

    private final HeaderJwtDecoder headerJwtDecoder;

    private final HandlerBus handlerBus;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/authentications",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthRestDTO> authenticate(
            @RequestBody SignInInputRestDTO signInInputRestDTO) {
        var params = authRestMapper.toSignInParams(signInInputRestDTO);

        var user = handlerBus.execute(params);

        return ResponseEntity.ok()
                .header(AUTHORIZATION, headerJwtDecoder.encode(user.getUsername().getValue(), user.getRoles()))
                .body(authRestMapper.toDTO(user));
    }
}
