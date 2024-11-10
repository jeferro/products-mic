package com.jeferro.products.parametrics.infrastructure.parametrics_rest.mappers;

import com.jeferro.products.parametrics.domain.models.Parametric;
import com.jeferro.products.parametrics.domain.models.ParametricId;
import com.jeferro.products.parametrics.infrastructure.parametrics_rest.dtos.ParametricRestDTO;
import com.jeferro.shared.mappers.MapstructConfig;
import com.jeferro.shared.mappers.ProjectionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class ParametricRestMapper extends ProjectionMapper<Parametric, ParametricId, ParametricRestDTO> {

  public static final ParametricRestMapper INSTANCE = Mappers.getMapper(ParametricRestMapper.class);
}
