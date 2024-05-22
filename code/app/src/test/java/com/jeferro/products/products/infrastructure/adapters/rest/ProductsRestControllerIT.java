package com.jeferro.products.products.infrastructure.adapters.rest;

import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.shared.application.StubHandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.RestControllerIT;
import com.jeferro.products.shared.infrastructure.adapters.utils.ApprovalUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ProductsRestController.class)
class ProductsRestControllerIT extends RestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StubHandlerBus stubHandlerBus;

    @Test
    void givenProducts_whenListProducts_thenExecutesListProductsCommand() throws Exception {
        var products = Products.createOf(
                ProductMother.apple(),
                ProductMother.pear()
        );
        stubHandlerBus.init(products);

        var requestBuilder = MockMvcRequestBuilders.get("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstCommandOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void givenAnProduct_whenCreateProduct_thenExecutesCreateProductCommand() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestContent = """
                {
                  "name": "%s"
                }"""
                .formatted(apple.getName());

        var requestBuilder = MockMvcRequestBuilders.post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT)
                .content(requestContent);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstCommandOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void givenAnProduct_whenGetProduct_thenExecutesGetProductCommand() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestBuilder = MockMvcRequestBuilders.get("/v1/products/" + apple.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstCommandOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void givenAnProduct_whenUpdateProduct_thenExecutesUpdateProductCommand() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestContent = """
                {
                  "name": "%s"
                }"""
                .formatted(apple.getName());

        var requestBuilder = MockMvcRequestBuilders.put("/v1/products/" + apple.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT)
                .content(requestContent);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstCommandOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void givenAnProduct_whenDeleteProduct_thenExecutesDeleteProductCommand() throws Exception {
        var apple = ProductMother.apple();
        stubHandlerBus.init(apple);

        var requestBuilder = MockMvcRequestBuilders.delete("/v1/products/" + apple.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstCommandOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

}