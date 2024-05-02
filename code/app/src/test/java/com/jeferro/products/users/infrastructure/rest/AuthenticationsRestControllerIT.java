package com.jeferro.products.users.infrastructure.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import com.jeferro.products.shared.infrastructure.rest.RestTestConfiguration;
import com.jeferro.products.users.application.SignInCommand;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.models.UserMother;
import com.jeferro.products.users.infrastructure.adapters.rest.AuthenticationsRestController;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Import(RestTestConfiguration.class)
@WebMvcTest(AuthenticationsRestController.class)
class AuthenticationsRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthRestResolver authRestResolver;

    @MockBean
    private HandlerBus handlerBus;

    @Nested
    public class AuthenticateTests {

        @Test
        void should_tryToAuthenticateUser_when_requestIsReceived() throws Exception {
            var username = "one-user";
            var plainPassword = "plain-password";

            var requestContent = "{"
                    + "\"username\": \"" + username + "\","
                    + "\"password\": \"" + plainPassword + "\""
                    + "}";

            when(authRestResolver.resolve())
                    .thenReturn(AuthMother.anonymous());

            var user = UserMother.user();
            ArgumentCaptor<SignInCommand> commandCaptor = ArgumentCaptor.forClass(SignInCommand.class);
            when(handlerBus.execute(commandCaptor.capture()))
                    .thenReturn(user);

            var requestBuilder = MockMvcRequestBuilders.post("/api/v1/authentications")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestContent);

            var result = mockMvc.perform(requestBuilder);

            assertSignInResponse(result, user);

            assertSignInCommand(commandCaptor, username, plainPassword);
        }

        private void assertSignInResponse(ResultActions result, User user) throws Exception {
            result.andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(user.getUsername().getValue()))
                    .andExpect(jsonPath("$.roles").isArray());
        }

        private void assertSignInCommand(ArgumentCaptor<SignInCommand> commandCaptor, String username, String plainPassword) {
            var command = commandCaptor.getValue();

            assertTrue(command.getAuth().isAnonymous());
            assertEquals(username, command.getUsername().getValue());
            assertEquals(plainPassword, command.getPlainPassword());
        }
    }
}