package com.jeferro.products.products.product_reviews.infrastructure.rest;

import com.jeferro.products.generated.rest.v1.apis.ProductReviewsApi;
import com.jeferro.products.generated.rest.v1.dtos.CreateProductReviewInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductReviewRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductReviewInputRestDTO;
import com.jeferro.products.products.product_reviews.infrastructure.rest.mappers.ProductReviewRestMapper;
import com.jeferro.shared.ddd.application.bus.HandlerBus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductReviewRestController implements ProductReviewsApi {

    private final ProductReviewRestMapper productReviewRestMapper = ProductReviewRestMapper.INSTANCE;

    private final HandlerBus handlerBus;

    @Override
    public List<ProductReviewRestDTO> searchProductReviews(String productCode) {
        var params = productReviewRestMapper.toSearchProductsParams(productCode);

        var productReviews = handlerBus.execute(params);

        return productReviewRestMapper.toDTO(productReviews);
    }

    @Override
    public ProductReviewRestDTO createProductReview(CreateProductReviewInputRestDTO inputRestDTO) {
        var params = productReviewRestMapper.toCreateProductReviewParams(inputRestDTO);

        var productReview = handlerBus.execute(params);

        return productReviewRestMapper.toDTO(productReview);
    }

    @Override
    public ProductReviewRestDTO getProductReview(String productReviewId) {
        var params = productReviewRestMapper.toGetProductReviewParams(productReviewId);

        var productReview = handlerBus.execute(params);

        return productReviewRestMapper.toDTO(productReview);
    }

    @Override
    public ProductReviewRestDTO updateProductReview(String productReviewId,
                                                    UpdateProductReviewInputRestDTO inputRestDTO) {
        var params = productReviewRestMapper.toUpdateProductReviewParams(productReviewId, inputRestDTO);

        var productReview = handlerBus.execute(params);

        return productReviewRestMapper.toDTO(productReview);
    }

    @Override
    public ProductReviewRestDTO deleteProductReview(String productReviewId) {
        var params = productReviewRestMapper.toDeleteProductReviewParams(productReviewId);

        var productReview = handlerBus.execute(params);

        return productReviewRestMapper.toDTO(productReview);
    }
}
