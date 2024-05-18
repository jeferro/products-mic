package com.jeferro.products.shared.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.shared.domain.events.EventId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class EventIdKafkaMapper extends IdentifierMapper<EventId, String> {

    public static final EventIdKafkaMapper INSTANCE = Mappers.getMapper(EventIdKafkaMapper.class);
}
