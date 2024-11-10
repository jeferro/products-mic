package com.jeferro.products.products.products.infrastructure.rest.mappers;

import java.util.List;

import com.jeferro.products.generated.rest.v1.dtos.CreateProductInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductFilterOrderRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.ProductSummaryRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductInputRestDTO;
import com.jeferro.products.generated.rest.v1.dtos.UpdateProductStatusInputRestDTO;
import com.jeferro.products.products.products.application.params.CreateProductParams;
import com.jeferro.products.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.products.application.params.GetProductParams;
import com.jeferro.products.products.products.application.params.SearchProductsParams;
import com.jeferro.products.products.products.application.params.UpdateProductParams;
import com.jeferro.products.products.products.application.params.UpdateProductStatusParams;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.products.products.products.domain.models.filter.ProductFilter;
import com.jeferro.products.products.products.domain.models.filter.ProductFilterOrder;
import com.jeferro.shared.mappers.MapstructConfig;
import com.jeferro.shared.mappers.PrimaryAggregateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class ProductRestMapper extends PrimaryAggregateMapper<Product, ProductCode, ProductRestDTO> {

  public static final ProductRestMapper INSTANCE = Mappers.getMapper(ProductRestMapper.class);

  public SearchProductsParams toSearchProductsParams(Integer pageNumber,
	  Integer pageSize,
	  ProductFilterOrderRestDTO orderRestDTO,
	  Boolean ascending,
	  String name) {
	var order = toDomain(orderRestDTO);
	var productFilter = new ProductFilter(pageNumber, pageSize, order, ascending, name);

	return new SearchProductsParams(productFilter);
  }

  public abstract ProductFilterOrder toDomain(ProductFilterOrderRestDTO orderRestDTO);

  public abstract CreateProductParams toCreateProductParams(CreateProductInputRestDTO productInputRestDTO);

  public abstract GetProductParams toGetProductParams(String productCode);

  @Mapping(target = "name", source = "inputRestDTO.name")
  public abstract UpdateProductParams toUpdateProductParams(String productCode, UpdateProductInputRestDTO inputRestDTO);

  @Mapping(target = "status", source = "inputRestDTO.status")
  public abstract UpdateProductStatusParams toUpdateProductStatusParams(
      String productCode,
	  UpdateProductStatusInputRestDTO inputRestDTO);

  public abstract DeleteProductParams toDeleteProductParams(String productCode);

  public abstract List<ProductSummaryRestDTO> toDTO(Products products);
}
