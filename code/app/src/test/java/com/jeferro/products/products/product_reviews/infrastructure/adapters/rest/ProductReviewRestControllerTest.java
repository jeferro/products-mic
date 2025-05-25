package com.jeferro.products.products.product_reviews.infrastructure.adapters.rest;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.product_reviews.infrastructure.rest.ProductReviewRestController;
import com.jeferro.products.products.products.domain.models.ProductMother;
import com.jeferro.products.shared.application.StubUseCaseBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.RestControllerTest;
import com.jeferro.products.shared.infrastructure.adapters.utils.ApprovalUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ProductReviewRestController.class)
class ProductReviewRestControllerTest extends RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StubUseCaseBus stubHandlerBus;

    @Test
    void execute_list_product_reviews_on_request() throws Exception {
        var apple = ProductMother.apple();
        var productReviews = ProductReviews.createOf(
                ProductReviewMother.userReviewOfApple(),
                ProductReviewMother.adminReviewOfApple()
        );
        stubHandlerBus.init(productReviews);

        var requestBuilder = MockMvcRequestBuilders.get("/v1/product-reviews?productCode=" + apple.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_EN)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_TOKEN);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void execute_create_product_on_request() throws Exception {
        var userReviewOfProduct = ProductReviewMother.userReviewOfApple();
        stubHandlerBus.init(userReviewOfProduct);

        var requestContent = """
                {
                  "productCode": "%s",
                  "comment": "%s"
                }"""
                .formatted(userReviewOfProduct.getProductCode(), userReviewOfProduct.getComment());

        var requestBuilder = MockMvcRequestBuilders.post("/v1/product-reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_EN)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_TOKEN)
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
        var userReviewOfProduct = ProductReviewMother.userReviewOfApple();
        stubHandlerBus.init(userReviewOfProduct);

        var requestBuilder = MockMvcRequestBuilders.get("/v1/product-reviews/" + userReviewOfProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_EN)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_TOKEN);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }

    @Test
    void execute_update_product_on_request() throws Exception {
        var userReviewOfProduct = ProductReviewMother.userReviewOfApple();
        stubHandlerBus.init(userReviewOfProduct);

        var requestContent = """
                {
                  "comment": "%s"
                }"""
                .formatted(userReviewOfProduct.getComment());

        var requestBuilder = MockMvcRequestBuilders.put("/v1/product-reviews/" + userReviewOfProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_EN)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_TOKEN)
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
        var userReviewOfProduct = ProductReviewMother.userReviewOfApple();
        stubHandlerBus.init(userReviewOfProduct);

        var requestBuilder = MockMvcRequestBuilders.delete("/v1/product-reviews/" + userReviewOfProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_EN)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_USER_TOKEN);

        var response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        ApprovalUtils.verifyAll(stubHandlerBus.getFirstParamOrError(),
                response.getStatus(),
                response.getContentAsString());
    }
}
