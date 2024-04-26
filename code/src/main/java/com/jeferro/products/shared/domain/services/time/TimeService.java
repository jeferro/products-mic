package com.jeferro.products.shared.domain.services.time;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public abstract class TimeService {

    private static TimeGenerator timeGenerator = new InstantTimeService();

    public static Instant now() {
        // TODO: We truncate to millis because mongo dates have a millis precision
        return timeGenerator.generate()
                .truncatedTo(ChronoUnit.MILLIS);
    }

    protected static void configure(TimeGenerator timeGenerator) {
        TimeService.timeGenerator = timeGenerator;
    }


}
