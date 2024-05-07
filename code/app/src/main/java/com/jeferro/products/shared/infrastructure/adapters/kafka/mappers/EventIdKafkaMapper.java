package com.jeferro.products.shared.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.shared.domain.events.EventId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper
@Profile(RestProfile.NAME)
public abstract class EventIdKafkaMapper extends IdentifierMapper<EventId, String> {

    public static final EventIdKafkaMapper INSTANCE = Mappers.getMapper(EventIdKafkaMapper.class);
}
