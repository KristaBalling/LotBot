package com.example.lotbot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateHelper {
    /**
     * Gets the number of hours between two LocalDateTime objects.
     *
     * (To get the current LocalDateTime, you can use LocalDateTime.now())
     * @param start the first LocalDateTime (e.g., when the car parks)
     * @param end the second localDateTime (e.g., when the car leaves)
     * @return The number of hours between those two dates (including partial hours)
     */
    public static double getHoursBetweenDates(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).get(ChronoUnit.SECONDS) / 3600.0;
    }
}
