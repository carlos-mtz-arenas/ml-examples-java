package org.learning.ml.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeTrackingUtils {

    public static void measureProcess(final String processName, final Runnable process) {
        final var startTime = LocalDateTime.now();
        process.run();
        final var endTime = LocalDateTime.now();
        System.out.println(processName + " took: [" + Duration.between(startTime, endTime).toMillis() + "] ms.");
    }
}
