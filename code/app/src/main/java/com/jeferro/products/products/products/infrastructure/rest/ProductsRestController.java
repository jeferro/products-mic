package com.jeferro.products.products.products.infrastructure.rest;

import java.util.List;

import com.jeferro.products.generated.rest.v1.apis.ProductsApi;
import com.jeferro.products.generated.rest.v1.dtos.ProductFilterOrderRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductSummaryRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductStatusInputRestDTO;
import com.jeferro.products.products.products.infrastructure.rest.mappers.ProductRestMapper;
import com.jeferro.shared.ddd.application.bus.HandlerBus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

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

	return productRestMapper.toDTO(products);
  }

  @Override
  public ProductRestDTO createProduct(ProductInputRestDTO productInputRestDTO) {
	var params = productRestMapper.toCreateProductParams(productInputRestDTO);

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
  public ProductRestDTO updateProduct(String productCode, ProductInputRestDTO productInputRestDTO) {
	var params = productRestMapper.toUpdateProductParams(productCode, productInputRestDTO);

	var user = handlerBus.execute(params);

	return productRestMapper.toDTO(user);
  }

  @Override
  public ProductRestDTO updateProductStatus(String productCode,
	  UpdateProductStatusInputRestDTO updateProductStatusInputRestDTO) {
	var params = productRestMapper.toUpdateProductStatusParams(productCode, updateProductStatusInputRestDTO);

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
