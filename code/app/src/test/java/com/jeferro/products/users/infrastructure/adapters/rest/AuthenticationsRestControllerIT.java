package com.jeferro.products.users.infrastructure.adapters.rest;

import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.RestControllerIT;
import com.jeferro.products.shared.infrastructure.adapters.utils.ApprovalUtils;
import com.jeferro.products.users.application.commands.SignInCommand;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.models.UserMother;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@WebMvcTest(AuthenticationsRestController.class)
class AuthenticationsRestControllerIT extends RestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HandlerBus handlerBus;


    @Test
    void givenAnUsers_whenSignIn_thenExecutesSignInCommand() throws Exception {
        var user = UserMother.user();
        ArgumentCaptor<SignInCommand> commandCaptor = givenAnAuthenticatedUserOnUserSignIn(user);

        var requestContent = """
                {
                  "username": "%s",
                  "password": "plain-password"
                }"""
                .formatted(user.getUsername());

        var requestBuilder = MockMvcRequestBuilders.post("/v1/authentications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(commandCaptor.getValue(),
                response.getStatus(),
                response.getContentAsString());
    }


    private ArgumentCaptor<SignInCommand> givenAnAuthenticatedUserOnUserSignIn(User user) {
        ArgumentCaptor<SignInCommand> commandCaptor = ArgumentCaptor.forClass(SignInCommand.class);

        when(handlerBus.execute(commandCaptor.capture()))
                .thenReturn(user);

        return commandCaptor;
    }
}