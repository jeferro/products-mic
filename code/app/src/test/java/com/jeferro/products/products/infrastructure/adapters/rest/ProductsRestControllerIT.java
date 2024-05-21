package com.jeferro.products.products.infrastructure.adapters.rest;

import com.jeferro.products.products.application.commands.*;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductMother;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.RestControllerIT;
import com.jeferro.products.shared.infrastructure.adapters.utils.ApprovalUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@WebMvcTest(ProductsRestController.class)
class ProductsRestControllerIT extends RestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HandlerBus handlerBus;

    @Nested
    public class ListProductsTests {

        @Test
        void givenProducts_whenListProducts_thenExecutesListProductsCommand() throws Exception {
            ArgumentCaptor<ListProductsCommand> commandCaptor = givenTwoProductsOnListProductCommand();

            var requestBuilder = MockMvcRequestBuilders.get("/v1/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT);

            var response = mockMvc.perform(requestBuilder)
                    .andReturn()
                    .getResponse();

            ApprovalUtils.verifyAll(commandCaptor.getValue(),
                    response.getStatus(),
                    response.getContentAsString());
        }
    }

    @Nested
    public class CreateProductTests {

        @Test
        void givenAnProduct_whenCreateProduct_thenExecutesCreateProductCommand() throws Exception {
            var apple = ProductMother.apple();
            ArgumentCaptor<CreateProductCommand> commandCaptor = givenAnProductOnCreateProductCommand(apple);

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

            ApprovalUtils.verifyAll(commandCaptor.getValue(),
                    response.getStatus(),
                    response.getContentAsString());
        }
    }

    @Nested
    public class GetProductTests {

        @Test
        void givenAnProduct_whenGetProduct_thenExecutesGetProductCommand() throws Exception {
            var apple = ProductMother.apple();
            ArgumentCaptor<GetProductCommand> commandCaptor = givenAnProductOnGetProductCommand(apple);

            var requestBuilder = MockMvcRequestBuilders.get("/v1/products/" + apple.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT);

            var response = mockMvc.perform(requestBuilder)
                    .andReturn()
                    .getResponse();

            ApprovalUtils.verifyAll(commandCaptor.getValue(),
                    response.getStatus(),
                    response.getContentAsString());
        }
    }

    @Nested
    public class UpdateProductTests {

        @Test
        void givenAnProduct_whenUpdateProduct_thenExecutesUpdateProductCommand() throws Exception {
            var apple = ProductMother.apple();
            ArgumentCaptor<UpdateProductCommand> commandCaptor = givenAnProductOnUpdateProductCommand(apple);

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

            ApprovalUtils.verifyAll(commandCaptor.getValue(),
                    response.getStatus(),
                    response.getContentAsString());
        }
    }

    @Nested
    public class DeleteProductTests {

        @Test
        void givenAnProduct_whenDeleteProduct_thenExecutesDeleteProductCommand() throws Exception {
            var apple = ProductMother.apple();
            ArgumentCaptor<DeleteProductCommand> commandCaptor = givenAnProductOnDeleteProductCommand(apple);

            var requestBuilder = MockMvcRequestBuilders.delete("/v1/products/" + apple.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_CONTENT);

            var response = mockMvc.perform(requestBuilder)
                    .andReturn()
                    .getResponse();

            ApprovalUtils.verifyAll(commandCaptor.getValue(),
                    response.getStatus(),
                    response.getContentAsString());
        }
    }

    private ArgumentCaptor<ListProductsCommand> givenTwoProductsOnListProductCommand() {
        var products = Products.createOf(
                ProductMother.apple(),
                ProductMother.pear()
        );

        ArgumentCaptor<ListProductsCommand> commandCaptor = ArgumentCaptor.forClass(ListProductsCommand.class);

        when(handlerBus.execute(commandCaptor.capture()))
                .thenReturn(products);

        return commandCaptor;
    }

    private ArgumentCaptor<CreateProductCommand> givenAnProductOnCreateProductCommand(Product apple) {
        ArgumentCaptor<CreateProductCommand> commandCaptor = ArgumentCaptor.forClass(CreateProductCommand.class);

        when(handlerBus.execute(commandCaptor.capture()))
                .thenReturn(apple);

        return commandCaptor;
    }

    private ArgumentCaptor<GetProductCommand> givenAnProductOnGetProductCommand(Product apple) {
        ArgumentCaptor<GetProductCommand> commandCaptor = ArgumentCaptor.forClass(GetProductCommand.class);

        when(handlerBus.execute(commandCaptor.capture()))
                .thenReturn(apple);

        return commandCaptor;
    }

    private ArgumentCaptor<UpdateProductCommand> givenAnProductOnUpdateProductCommand(Product apple) {
        ArgumentCaptor<UpdateProductCommand> commandCaptor = ArgumentCaptor.forClass(UpdateProductCommand.class);

        when(handlerBus.execute(commandCaptor.capture()))
                .thenReturn(apple);

        return commandCaptor;
    }

    private ArgumentCaptor<DeleteProductCommand> givenAnProductOnDeleteProductCommand(Product apple) {
        ArgumentCaptor<DeleteProductCommand> commandCaptor = ArgumentCaptor.forClass(DeleteProductCommand.class);

        when(handlerBus.execute(commandCaptor.capture()))
                .thenReturn(apple);

        return commandCaptor;
    }
}