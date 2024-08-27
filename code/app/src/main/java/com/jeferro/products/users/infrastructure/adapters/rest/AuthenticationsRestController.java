package com.jeferro.products.users.infrastructure.adapters.rest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.jeferro.products.components.rest.generated.apis.AuthenticationsApi;
import com.jeferro.products.components.rest.generated.dtos.AuthRestDTO;
import com.jeferro.products.components.rest.generated.dtos.SignInInputRestDTO;
import com.jeferro.products.components.rest.shared.securtiy.dtos.JwtToken;
import com.jeferro.products.components.rest.shared.securtiy.services.JwtDecoder;
import com.jeferro.shared.application.bus.HandlerBus;
import com.jeferro.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import com.jeferro.products.users.application.commands.SignInCommand;
import com.jeferro.products.users.infrastructure.adapters.rest.mappers.AuthRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationsRestController implements AuthenticationsApi {

	private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

	private final AuthRestMapper authRestMapper = AuthRestMapper.INSTANCE;

	private final AuthRestResolver authRestResolver;

	private final JwtDecoder jwtDecoder;

	private final HandlerBus handlerBus;

	public AuthenticationsRestController(AuthRestResolver authRestResolver, JwtDecoder jwtDecoder, HandlerBus handlerBus) {
		this.authRestResolver = authRestResolver;
		this.jwtDecoder = jwtDecoder;
		this.handlerBus = handlerBus;
	}

	@Override
	public ResponseEntity<AuthRestDTO> authenticate(SignInInputRestDTO signInInputRestDTO) {
		var command = new SignInCommand(
			authRestResolver.resolve(),
			usernameRestMapper.toDomain(signInInputRestDTO.getUsername()),
			signInInputRestDTO.getPassword()
		);

		var user = handlerBus.execute(command);

		var jwtToken = new JwtToken(user.getUsername().getValue(), user.getRoles());
		var jwtHeader = jwtDecoder.encode(jwtToken);

		return ResponseEntity.ok()
			.header(AUTHORIZATION, jwtHeader)
			.body(authRestMapper.toDTO(user));
	}
}
