package ru.antonsibgatulin.utils;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class TImeUtils {
    public static ChronoUnit getChronoUnit(TimeUnit timeUnit) {
        if (timeUnit == TimeUnit.SECONDS) {
            return ChronoUnit.SECONDS;
        } else if (timeUnit == TimeUnit.MINUTES) {
            return ChronoUnit.MINUTES;
        } else if (timeUnit == TimeUnit.HOURS) {
            return ChronoUnit.HOURS;
        } else if (timeUnit == TimeUnit.DAYS) {
            return ChronoUnit.DAYS;
        } else {
            throw new IllegalArgumentException("Unsupported TimeUnit: " + timeUnit);
        }
    }
}
