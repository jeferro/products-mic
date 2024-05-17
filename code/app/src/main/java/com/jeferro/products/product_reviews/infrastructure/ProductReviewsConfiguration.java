package com.jeferro.products.product_reviews.infrastructure;

import com.jeferro.products.product_reviews.application.CreateProductReviewHandler;
import com.jeferro.products.product_reviews.application.DeleteProductReviewHandler;
import com.jeferro.products.product_reviews.application.ListProductReviewHandler;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductReviewsConfiguration {

    @Bean
    public ListProductReviewHandler listProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
        return new ListProductReviewHandler(productReviewsRepository);
    }

    @Bean
    public CreateProductReviewHandler createProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
        return new CreateProductReviewHandler(productReviewsRepository);
    }

    @Bean
    public DeleteProductReviewHandler deleteProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
        return new DeleteProductReviewHandler(productReviewsRepository);
    }
}
