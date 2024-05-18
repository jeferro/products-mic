package com.jeferro.products.product_reviews.infrastructure;

import com.jeferro.products.product_reviews.application.CreateProductReviewHandler;
import com.jeferro.products.product_reviews.application.DeleteProductReviewHandler;
import com.jeferro.products.product_reviews.application.GetProductReviewHandler;
import com.jeferro.products.product_reviews.application.ListProductReviewHandler;
import com.jeferro.products.product_reviews.application.UpdateProductReviewHandler;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.shared.domain.events.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductReviewsConfiguration {

    @Bean
    public ListProductReviewHandler listProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
        return new ListProductReviewHandler(productReviewsRepository);
    }

    @Bean
    public CreateProductReviewHandler createProductReviewHandler(ProductsRepository productsRepository,
        ProductReviewsRepository productReviewsRepository,
        EventBus eventBus) {
        return new CreateProductReviewHandler(productsRepository, productReviewsRepository, eventBus);
    }

    @Bean
    public GetProductReviewHandler getProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
        return new GetProductReviewHandler(productReviewsRepository);
    }

    @Bean
    public UpdateProductReviewHandler updateProductReviewHandler(ProductReviewsRepository productReviewsRepository,
        EventBus eventBus) {
        return new UpdateProductReviewHandler(productReviewsRepository, eventBus);
    }

    @Bean
    public DeleteProductReviewHandler deleteProductReviewHandler(ProductReviewsRepository productReviewsRepository,
        EventBus eventBus) {
        return new DeleteProductReviewHandler(productReviewsRepository, eventBus);
    }
}
