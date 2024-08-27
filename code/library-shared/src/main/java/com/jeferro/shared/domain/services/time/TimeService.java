package com.jeferro.shared.domain.services.time;

import java.time.Instant;

public abstract class TimeService {

    private static TimeGenerator timeGenerator = new InstantTimeService();

    public static Instant now() {
        return timeGenerator.generate();
    }

    protected static void configure(TimeGenerator timeGenerator) {
        TimeService.timeGenerator = timeGenerator;
    }


}
