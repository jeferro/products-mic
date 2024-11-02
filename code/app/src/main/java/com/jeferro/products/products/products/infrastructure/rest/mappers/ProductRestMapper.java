package com.jeferro.products.products.products.infrastructure.rest.mappers;

import com.jeferro.products.generated.rest.v1.dtos.ProductInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductStatusInputRestDTO;
import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.products.application.params.GetProductParams;
import com.jeferro.products.products.products.application.params.ListProductsParams;
import com.jeferro.products.products.products.application.params.UpdateProductParams;
import com.jeferro.products.products.products.application.params.UpdateProductStatusParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductCriteria;
import com.jeferro.shared.mappers.MapstructConfig;
import com.jeferro.shared.mappers.PrimaryAggregateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class ProductRestMapper extends PrimaryAggregateMapper<Product, ProductCode, ProductRestDTO> {

  public static final ProductRestMapper INSTANCE = Mappers.getMapper(ProductRestMapper.class);

  public abstract ProductCriteria toDomain(Integer pageNumber, Integer pageSize, String name);

  public ListProductsParams toListProductsParams(Integer pageNumber, Integer pageSize, String name) {
	var productCriteria = toDomain(pageNumber, pageSize, name);

	return new ListProductsParams(productCriteria);
  }

  @Mapping(target = "productCode.value", source = "code")
  public abstract CreateProductParams toCreateProductParams(ProductInputRestDTO productInputRestDTO);

  public abstract GetProductParams toGetProductParams(String productCode);

  @Mapping(target = "name", source = "inputRestDTO.name")
  public abstract UpdateProductParams toUpdateProductParams(String productCode, ProductInputRestDTO inputRestDTO);

  @Mapping(target = "status", source = "inputRestDTO.status")
  public abstract UpdateProductStatusParams toUpdateProductStatusParams(
      String productCode,
	  UpdateProductStatusInputRestDTO inputRestDTO);

  public abstract DeleteProductParams toDeleteProductParams(String productCode);
}