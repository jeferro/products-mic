package com.jeferro.products.shared.infrastructure.integrations.mongo.mappers;

import com.jeferro.products.shared.domain.models.metadata.Metadata;
import com.jeferro.products.shared.infrastructure.integrations.mappers.BidirectionalMapper;
import com.jeferro.products.shared.infrastructure.integrations.mongo.dtos.MetadataMongoDTO;

public class MetadataMongoMapper extends BidirectionalMapper<Metadata, MetadataMongoDTO> {

    public static final MetadataMongoMapper INSTANCE = new MetadataMongoMapper();

    @Override
    public Metadata toDomain(MetadataMongoDTO metadataMongoDTO) {
        return new Metadata(
                metadataMongoDTO.createdAt(),
                metadataMongoDTO.createdBy(),
                metadataMongoDTO.updatedAt(),
                metadataMongoDTO.updatedBy()
        );
    }

    @Override
    public MetadataMongoDTO toDTO(Metadata metadata) {
        return new MetadataMongoDTO(
                metadata.getCreatedAt(),
                metadata.getCreatedBy(),
                metadata.getUpdatedAt(),
                metadata.getUpdatedBy()
        );
    }
}
