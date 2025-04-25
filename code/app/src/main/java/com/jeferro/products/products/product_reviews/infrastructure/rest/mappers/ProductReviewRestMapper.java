package com.jeferro.products.products.product_reviews.infrastructure.rest.mappers;

import com.jeferro.products.generated.rest.v1.dtos.CreateProductReviewInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductReviewRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductReviewInputRestDTO;
import com.jeferro.products.products.product_reviews.application.params.*;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.mappers.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public abstract class ProductReviewRestMapper {

    public static final ProductReviewRestMapper INSTANCE = Mappers.getMapper(ProductReviewRestMapper.class);

    public abstract ProductReviewRestDTO toDTO(ProductReview productReview);

    public List<ProductReviewRestDTO> toDTO(ProductReviews productReviews) {
        return productReviews.map(this::toDTO).toList();
    }

    public abstract ProductReviewId toDomain(String productCode, String username);

    public SearchProductReviewParams toSearchProductsParams(String productCodeRestDTO) {
        var productCode = new ProductCode(productCodeRestDTO);
        return new SearchProductReviewParams(productCode);
    }

    public abstract CreateProductReviewParams toCreateProductReviewParams(String productCode,
                                                                          CreateProductReviewInputRestDTO inputRestDTO);

    @Mapping(target = "productReviewId", expression = "java(toDomain(productCode, username))")
    public abstract GetProductReviewParams toGetProductReviewParams(String productCode,
                                                                    String username);

    @Mapping(target = "productReviewId", expression = "java(toDomain(productCode, username))")
    public abstract UpdateProductReviewParams toUpdateProductReviewParams(String productCode,
                                                                          String username,
                                                                          UpdateProductReviewInputRestDTO inputRestDTO);

    @Mapping(target = "productReviewId", expression = "java(toDomain(productCode, username))")
    public abstract DeleteProductReviewParams toDeleteProductReviewParams(String productCode,
                                                                          String username);
}
