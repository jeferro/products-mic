package com.jeferro.products.products.infrastructure;

import com.jeferro.products.products.application.CreateProductHandler;
import com.jeferro.products.products.application.DeleteProductHandler;
import com.jeferro.products.products.application.GetProductHandler;
import com.jeferro.products.products.application.ListProductsHandler;
import com.jeferro.products.products.application.UpdateProductHandler;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.shared.domain.events.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsConfiguration {

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
    public GetProductHandler getProductHandler(ProductsRepository productsRepository) {
        return new GetProductHandler(productsRepository);
    }

    @Bean
    public UpdateProductHandler updateProductHandler(ProductsRepository productsRepository,
                                                     EventBus eventBus) {
        return new UpdateProductHandler(productsRepository, eventBus);
    }

    @Bean
    public DeleteProductHandler deleteProductHandler(ProductsRepository productsRepository,
                                                     EventBus eventBus) {
        return new DeleteProductHandler(productsRepository, eventBus);
    }
}
