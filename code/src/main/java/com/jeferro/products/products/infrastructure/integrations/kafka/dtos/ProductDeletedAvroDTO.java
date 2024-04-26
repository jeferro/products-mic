package com.jeferro.products.products.infrastructure.integrations.kafka.dtos;

import java.time.Instant;

@Deprecated
public record ProductDeletedAvroDTO(
        Instant occurredOn,
        String productId
){ }
