package com.jeferro.products.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.products.products.domain.models.ProductCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductCriteriaRestMapper {

    public static final ProductCriteriaRestMapper INSTANCE = Mappers.getMapper(ProductCriteriaRestMapper.class);

    public ProductCriteria toDomain(int pageNumber, int pageSize, String name) {
        return new ProductCriteria(pageNumber, pageSize, name);
    }
}
