package com.jeferro.products.products.infrastructure.adapters.rest;

import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.shared.application.StubHandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.RestControllerTest;
import com.jeferro.products.shared.infrastructure.adapters.utils.ApprovalUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ProductsRestController.class)
class ProductsRestControllerTest extends RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StubHandlerBus stubHandlerBus;

    @Test
    void execute_list_products_on_request() throws Exception {
        var products = Products.createOf(
                ProductMother.apple(),
                ProductMother.pear()
        );
        stubHandlerBus.init(products);

        var requestBuilder = MockMvcRequestBuilders.get("/v1/products?pageNumber=0&pageSize=10&name=apple")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_CONTENT);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void execute_create_product_on_request() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestContent = """
                {
                  "code": "%s",
                  "name": "%s"
                }"""
                .formatted(apple.getCode(), apple.getName());

        var requestBuilder = MockMvcRequestBuilders.post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_CONTENT)
                .content(requestContent);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void execute_get_product_on_request() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestBuilder = MockMvcRequestBuilders.get("/v1/products/" + apple.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_CONTENT);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void execute_update_product_on_request() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestContent = """
                {
                  "name": "%s"
                }"""
                .formatted(apple.getName());

        var requestBuilder = MockMvcRequestBuilders.put("/v1/products/" + apple.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_CONTENT)
                .content(requestContent);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void execute_delete_product_on_request() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestBuilder = MockMvcRequestBuilders.delete("/v1/products/" + apple.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_CONTENT);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

}