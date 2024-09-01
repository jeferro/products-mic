package com.jeferro.products.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.generated.rest.v1.dtos.ProductStatusRestDTO;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductStatusRestMapper extends BidirectionalMapper<ProductStatus, ProductStatusRestDTO> {

  public static final ProductStatusRestMapper INSTANCE = Mappers.getMapper(ProductStatusRestMapper.class);
}
