package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper
@Profile(KafkaProfile.NAME)
public abstract class ProductIdKafkaMapper extends IdentifierMapper<ProductId, String> {

	public static final ProductIdKafkaMapper INSTANCE = Mappers.getMapper(ProductIdKafkaMapper.class);

}
