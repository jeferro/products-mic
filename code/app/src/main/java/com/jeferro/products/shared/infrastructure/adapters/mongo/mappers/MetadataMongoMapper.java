package com.jeferro.products.shared.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.shared.dtos.MetadataMongoDTO;
import com.jeferro.products.shared.domain.models.metadata.Metadata;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class MetadataMongoMapper extends BidirectionalMapper<Metadata, MetadataMongoDTO> {

	public static final MetadataMongoMapper instance = Mappers.getMapper(MetadataMongoMapper.class);
}
