package com.jeferro.products.products.infrastructure;

import com.jeferro.products.products.application.*;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.products.domain.services.ProductFetcher;
import com.jeferro.products.shared.domain.events.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsConfiguration {

    @Bean
    public ProductFetcher productFetcher(ProductsRepository productsRepository) {
        return new ProductFetcher(productsRepository);
    }

    @Bean
    public ListProductsHandler listProductsHandler(ProductsRepository productsRepository) {
        return new ListProductsHandler(productsRepository);
    }

    @Bean
    public CreateProductHandler createProductHandler(ProductsRepository productsRepository,
                                                     EventBus eventBus) {
        return new CreateProductHandler(productsRepository, eventBus);
    }

    @Bean
    public GetProductHandler getProductHandler(ProductFetcher productFetcher) {
        return new GetProductHandler(productFetcher);
    }

    @Bean
    public UpdateProductHandler updateProductHandler(ProductFetcher productFetcher,
                                                     ProductsRepository productsRepository,
                                                     EventBus eventBus) {
        return new UpdateProductHandler(productFetcher, productsRepository, eventBus);
    }

    @Bean
    public DeleteProductHandler deleteProductHandler(ProductFetcher productFetcher,
                                                     ProductsRepository productsRepository,
                                                     EventBus eventBus) {
        return new DeleteProductHandler(productFetcher, productsRepository, eventBus);
    }
}
