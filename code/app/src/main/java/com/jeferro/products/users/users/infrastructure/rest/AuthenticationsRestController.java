package com.jeferro.products.users.users.infrastructure.rest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.jeferro.products.users.users.infrastructure.rest.dtos.AuthRestDTO;
import com.jeferro.products.users.users.infrastructure.rest.dtos.SignInInputRestDTO;
import com.jeferro.products.users.users.infrastructure.rest.mappers.AuthRestMapper;
import com.jeferro.shared.auth.infrastructure.rest.jwt.JwtDecoder;
import com.jeferro.shared.ddd.application.bus.HandlerBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationsRestController {

  private final AuthRestMapper authRestMapper = AuthRestMapper.INSTANCE;

  private final JwtDecoder jwtDecoder;

  private final HandlerBus handlerBus;

  public AuthenticationsRestController(JwtDecoder jwtDecoder, HandlerBus handlerBus) {
	this.jwtDecoder = jwtDecoder;
	this.handlerBus = handlerBus;
  }

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
		.header(AUTHORIZATION, jwtDecoder.encode(user.getUsername(), user.getRoles()))
		.body(authRestMapper.toDTO(user));
  }
}
