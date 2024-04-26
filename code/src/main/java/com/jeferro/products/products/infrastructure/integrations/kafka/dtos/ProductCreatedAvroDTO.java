package com.jeferro.products.products.infrastructure.integrations.kafka.dtos;

import java.time.Instant;

@Deprecated
public record ProductCreatedAvroDTO(
        Instant occurredOn,
        String productId
){ }
