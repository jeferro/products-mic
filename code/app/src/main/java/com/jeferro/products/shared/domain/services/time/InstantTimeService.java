package com.jeferro.products.shared.domain.services.time;

import java.time.Instant;

public class InstantTimeService implements TimeGenerator {

    @Override
    public Instant generate() {
        return Instant.now();
    }
}
