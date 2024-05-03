package com.jeferro.products.products.infrastructure.adapters.rest;

import java.util.List;

import com.jeferro.products.components.rest.generated.apis.ProductsApi;
import com.jeferro.products.components.rest.generated.dtos.ProductInputRestDTO;
import com.jeferro.products.components.rest.generated.dtos.ProductRestDTO;
import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.products.application.CreateProductCommand;
import com.jeferro.products.products.application.DeleteProductCommand;
import com.jeferro.products.products.application.GetProductCommand;
import com.jeferro.products.products.application.ListProductsCommand;
import com.jeferro.products.products.application.UpdateProductCommand;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductIdRestMapper;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductRestMapper;
import com.jeferro.products.shared.application.bus.HandlerBus;
import com.jeferro.products.shared.infrastructure.adapters.rest.services.AuthRestResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile(RestProfile.NAME)
public class ProductsRestController implements ProductsApi {

	private final ProductRestMapper productRestMapper = ProductRestMapper.INSTANCE;

	private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

	private final AuthRestResolver authRestResolver;

	private final HandlerBus handlerBus;

	public ProductsRestController(AuthRestResolver authRestResolver, HandlerBus handlerBus) {
		this.authRestResolver = authRestResolver;
		this.handlerBus = handlerBus;
	}

	@Override
	public ResponseEntity<List<ProductRestDTO>> listProducts() {
		var command = new ListProductsCommand(
			authRestResolver.resolve()
		);

		var products = handlerBus.execute(command);

		return productRestMapper.toOkResponseDTO(products);
	}

	@Override
	public ResponseEntity<ProductRestDTO> createProduct(ProductInputRestDTO productInputRestDTO) {
		var command = new CreateProductCommand(
			authRestResolver.resolve(),
			productInputRestDTO.getName()
		);

		var product = handlerBus.execute(command);

		return productRestMapper.toCreatedResponseDTO(product);
	}

	@Override
	public ResponseEntity<ProductRestDTO> updateProduct(String productId, ProductInputRestDTO productInputRestDTO) {
		var command = new UpdateProductCommand(
			authRestResolver.resolve(),
			productIdRestMapper.toDomain(productId),
			productInputRestDTO.getName()
		);

		var user = handlerBus.execute(command);

		return productRestMapper.toOkResponseDTO(user);
	}

	@Override
	public ResponseEntity<ProductRestDTO> getProduct(String productId) {
		var command = new GetProductCommand(
			authRestResolver.resolve(),
			productIdRestMapper.toDomain(productId)
		);

		var product = handlerBus.execute(command);

		return productRestMapper.toOkResponseDTO(product);
	}

	@Override
	public ResponseEntity<ProductRestDTO> deleteProduct(String productId) {
		var command = new DeleteProductCommand(
			authRestResolver.resolve(),
			productIdRestMapper.toDomain(productId)
		);

		var user = handlerBus.execute(command);

		return productRestMapper.toOkResponseDTO(user);
	}
}
