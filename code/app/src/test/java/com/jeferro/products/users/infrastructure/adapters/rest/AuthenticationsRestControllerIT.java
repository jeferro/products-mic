package com.jeferro.products.users.infrastructure.adapters.rest;

import static org.mockito.Mockito.when;

import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.RestControllerIT;
import com.jeferro.products.shared.infrastructure.adapters.utils.ApprovalUtils;
import com.jeferro.products.users.application.commands.SignInCommand;
import com.jeferro.products.users.domain.models.UserMother;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(AuthenticationsRestController.class)
class AuthenticationsRestControllerIT extends RestControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private HandlerBus handlerBus;

  @Nested
  public class AuthenticateTests {

	@Test
	void givenCredentials_whenSignIn_thenExecutesSignInCommand() throws Exception {
	  var requestContent = """
		{
		  "username": "one-user",
		  "password": "plain-password"
		}""";

	  var user = UserMother.user();
	  ArgumentCaptor<SignInCommand> commandCaptor = ArgumentCaptor.forClass(SignInCommand.class);
	  when(handlerBus.execute(commandCaptor.capture()))
		  .thenReturn(user);

	  var requestBuilder = MockMvcRequestBuilders.post("/v1/authentications")
		  .contentType(MediaType.APPLICATION_JSON)
		  .content(requestContent);

	  var responseContent = mockMvc.perform(requestBuilder)
		  .andReturn()
		  .getResponse()
		  .getContentAsString();

	  var command = commandCaptor.getValue();

	  ApprovalUtils.verifyAll(command, responseContent);
	}
  }
}