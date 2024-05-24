package com.jeferro.products.users.infrastructure.adapters.rest;

import com.jeferro.products.shared.application.StubHandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.RestControllerIT;
import com.jeferro.products.shared.infrastructure.adapters.utils.ApprovalUtils;
import com.jeferro.products.users.domain.models.UserMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(AuthenticationsRestController.class)
class AuthenticationsRestControllerIT extends RestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StubHandlerBus stubHandlerBus;

    @Test
    void execute_sign_in_on_request() throws Exception {
        var user = UserMother.user();
        stubHandlerBus.init(user);

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

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstCommandOrError(),
                response.getStatus(),
                response.getContentAsString());
    }
}