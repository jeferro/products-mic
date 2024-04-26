package com.jeferro.products.shared.infrastructure.integrations.rest.mappers;

import com.jeferro.products.shared.domain.models.metadata.Metadata;
import com.jeferro.products.shared.infrastructure.integrations.mappers.BidirectionalMapper;
import com.jeferro.products.shared.infrastructure.integrations.rest.dtos.MetadataRestDTO;

public class MetadataRestMapper extends BidirectionalMapper<Metadata, MetadataRestDTO> {

    public static final MetadataRestMapper INSTANCE = new MetadataRestMapper();

    @Override
    public Metadata toDomain(MetadataRestDTO metadataMongoDTO) {
        return new Metadata(
                metadataMongoDTO.createdAt(),
                metadataMongoDTO.createdBy(),
                metadataMongoDTO.updatedAt(),
                metadataMongoDTO.updatedBy()
        );
    }

    @Override
    public MetadataRestDTO toDTO(Metadata metadata) {
        return new MetadataRestDTO(
                metadata.getCreatedAt(),
                metadata.getCreatedBy(),
                metadata.getUpdatedAt(),
                metadata.getUpdatedBy()
        );
    }
}
