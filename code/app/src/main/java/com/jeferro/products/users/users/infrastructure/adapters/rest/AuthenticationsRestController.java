package com.jeferro.products.users.users.infrastructure.adapters.rest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.jeferro.products.generated.rest.v1.apis.AuthenticationsApi;
import com.jeferro.products.generated.rest.v1.dtos.AuthRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.SignInInputRestDTO;
import com.jeferro.products.users.users.application.params.SignInParams;
import com.jeferro.products.users.users.infrastructure.adapters.rest.mappers.AuthRestMapper;
import com.jeferro.shared.application.HandlerBus;
import com.jeferro.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.shared.infrastructure.adapters.rest.services.jwt.JwtDecoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationsRestController implements AuthenticationsApi {

	private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

	private final AuthRestMapper authRestMapper = AuthRestMapper.INSTANCE;

	private final JwtDecoder jwtDecoder;

	private final HandlerBus handlerBus;

	public AuthenticationsRestController(JwtDecoder jwtDecoder, HandlerBus handlerBus) {
		this.jwtDecoder = jwtDecoder;
		this.handlerBus = handlerBus;
	}

	@Override
	public ResponseEntity<AuthRestDTO> authenticate(SignInInputRestDTO signInInputRestDTO) {
		var params = new SignInParams(
			usernameRestMapper.toDomain(signInInputRestDTO.getUsername()),
			signInInputRestDTO.getPassword()
		);

		var user = handlerBus.execute(params);

		return ResponseEntity.ok()
			.header(AUTHORIZATION, jwtDecoder.encode(user.getUsername(), user.getRoles()))
			.body(authRestMapper.toDTO(user));
	}
}
