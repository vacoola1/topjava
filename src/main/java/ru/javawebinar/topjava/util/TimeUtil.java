package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isTimeBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        LocalTime startTimeOrMin = startTime == null ? LocalTime.MIN : startTime;
        LocalTime endTimeOrMax = (endTime == null || endTime.equals(LocalTime.MIN)) ? LocalTime.MAX : endTime;
        return lt.compareTo(startTimeOrMin) >= 0 && lt.compareTo(endTimeOrMax) <= 0;
    }

    public static boolean isDateBetween(LocalDate lt, LocalDate startDate, LocalDate endDate) {
        LocalDate startDateOrMin = startDate == null ? LocalDate.MIN : startDate;
        LocalDate endDateOrMax = (endDate == null || endDate.equals(LocalDate.MIN)) ? LocalDate.MAX : endDate;
        return lt.compareTo(startDateOrMin) >= 0 && lt.compareTo(endDateOrMax) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TME_FORMATTER);
    }
}
