package com.jeferro.products.products.products.infrastructure.adapters.rest;

import java.util.List;

import com.jeferro.products.generated.rest.v1.apis.ProductsApi;
import com.jeferro.products.generated.rest.v1.dtos.ProductInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductStatusInputRestDTO;
import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.products.application.params.GetProductParams;
import com.jeferro.products.products.products.application.params.ListProductsParams;
import com.jeferro.products.products.products.application.params.UpdateProductParams;
import com.jeferro.products.products.products.application.params.UpdateProductStatusParams;
import com.jeferro.products.products.products.infrastructure.adapters.rest.mappers.ProductCodeRestMapper;
import com.jeferro.products.products.products.infrastructure.adapters.rest.mappers.ProductCriteriaRestMapper;
import com.jeferro.products.products.products.infrastructure.adapters.rest.mappers.ProductRestMapper;
import com.jeferro.products.products.products.infrastructure.adapters.rest.mappers.ProductStatusRestMapper;
import com.jeferro.shared.ddd.application.HandlerBus;
import com.jeferro.shared.locale.infrastructure.adapters.rest.mappers.LocalizedDataRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsRestController implements ProductsApi {

	private final ProductRestMapper productRestMapper = ProductRestMapper.INSTANCE;

	private final ProductCodeRestMapper productCodeRestMapper = ProductCodeRestMapper.INSTANCE;

	private final ProductCriteriaRestMapper productCriteriaRestMapper = ProductCriteriaRestMapper.INSTANCE;

	private final ProductStatusRestMapper productStatusRestMapper = ProductStatusRestMapper.INSTANCE;

	private final LocalizedDataRestMapper localizedDataRestMapper = LocalizedDataRestMapper.INSTANCE;

	private final HandlerBus handlerBus;

	public ProductsRestController(HandlerBus handlerBus) {
		this.handlerBus = handlerBus;
	}

	@Override
	public ResponseEntity<List<ProductRestDTO>> listProducts(Integer pageNumber, Integer pageSize, String name) {
		var params = new ListProductsParams(
			productCriteriaRestMapper.toDomain(pageNumber, pageSize, name)
		);

		var products = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(products);
	}

	@Override
	public ResponseEntity<ProductRestDTO> createProduct(ProductInputRestDTO productInputRestDTO) {
		var params = new CreateProductParams(
			productCodeRestMapper.toDomain(productInputRestDTO.getCode()),
			localizedDataRestMapper.toDomain(productInputRestDTO.getName())
		);

		var product = handlerBus.execute(params);

		return productRestMapper.toCreatedResponseDTO(product);
	}

	@Override
	public ResponseEntity<ProductRestDTO> getProduct(String productCode) {
		var params = new GetProductParams(
			productCodeRestMapper.toDomain(productCode)
		);

		var product = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(product);
	}

	@Override
	public ResponseEntity<ProductRestDTO> updateProduct(String productCode, ProductInputRestDTO productInputRestDTO) {
		var params = new UpdateProductParams(
				productCodeRestMapper.toDomain(productCode),
			localizedDataRestMapper.toDomain(productInputRestDTO.getName())
		);

		var user = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(user);
	}

  @Override
  public ResponseEntity<ProductRestDTO> updateProductStatus(String productCode,
	  UpdateProductStatusInputRestDTO updateProductStatusInputRestDTO) {
	var params = new UpdateProductStatusParams(
		productCodeRestMapper.toDomain(productCode),
		productStatusRestMapper.toDomain(updateProductStatusInputRestDTO.getValue())
	);

	var user = handlerBus.execute(params);

	return productRestMapper.toOkResponseDTO(user);
  }

  @Override
	public ResponseEntity<ProductRestDTO> deleteProduct(String productCode) {
		var params = new DeleteProductParams(
			productCodeRestMapper.toDomain(productCode)
		);

		var user = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(user);
	}
}
