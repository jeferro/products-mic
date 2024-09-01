package com.jeferro.shared.ddd.infrastructure.adapters.kafka.mappers;

import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class EventIdKafkaMapper extends IdentifierMapper<EventId, String> {

    public static final EventIdKafkaMapper INSTANCE = Mappers.getMapper(EventIdKafkaMapper.class);
}
