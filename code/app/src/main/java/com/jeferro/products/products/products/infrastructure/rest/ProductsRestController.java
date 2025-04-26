package com.jeferro.products.products.products.infrastructure.rest;

import com.jeferro.products.generated.rest.v1.apis.ProductsApi;
import com.jeferro.products.generated.rest.v1.dtos.*;
import com.jeferro.products.products.products.infrastructure.rest.mappers.ProductRestMapper;
import com.jeferro.shared.ddd.application.bus.HandlerBus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsRestController implements ProductsApi {

    private final ProductRestMapper productRestMapper = ProductRestMapper.INSTANCE;

    private final HandlerBus handlerBus;

    @Override
    public List<ProductSummaryRestDTO> searchProducts(Integer pageNumber, Integer pageSize,
                                                      ProductFilterOrderRestDTO order,
                                                      Boolean ascending,
                                                      String name) {
        var params = productRestMapper.toSearchProductsParams(pageNumber, pageSize, order, ascending, name);

        var products = handlerBus.execute(params);

        return productRestMapper.toSummaryDTO(products);
    }

    @Override
    public ProductRestDTO createProduct(CreateProductInputRestDTO createProductInputRestDTO) {
        var params = productRestMapper.toCreateProductParams(createProductInputRestDTO);

        var product = handlerBus.execute(params);

        return productRestMapper.toDTO(product);
    }

    @Override
    public ProductRestDTO getProduct(String productCode) {
        var params = productRestMapper.toGetProductParams(productCode);

        var product = handlerBus.execute(params);

        return productRestMapper.toDTO(product);
    }

    @Override
    public ProductRestDTO updateProduct(String productCode, UpdateProductInputRestDTO updateProductInputRestDTO) {
        var params = productRestMapper.toUpdateProductParams(productCode, updateProductInputRestDTO);

        var user = handlerBus.execute(params);

        return productRestMapper.toDTO(user);
    }

    @Override
    public ProductRestDTO publishProduct(String productCode) {
        var params = productRestMapper.toPublishProductParams(productCode);

        var user = handlerBus.execute(params);

        return productRestMapper.toDTO(user);
    }

    @Override
    public ProductRestDTO unpublishProduct(String productCode) {
        var params = productRestMapper.toUnpublishProductParams(productCode);

        var user = handlerBus.execute(params);

        return productRestMapper.toDTO(user);
    }

    @Override
    public ProductRestDTO deleteProduct(String productCode) {
        var params = productRestMapper.toDeleteProductParams(productCode);

        var user = handlerBus.execute(params);

        return productRestMapper.toDTO(user);
    }
}
