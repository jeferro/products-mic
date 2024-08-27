package com.jeferro.shared.infrastructure.adapters.kafka.mappers;

import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class EventIdKafkaMapper extends IdentifierMapper<EventId, String> {

    public static final EventIdKafkaMapper INSTANCE = Mappers.getMapper(EventIdKafkaMapper.class);
}
