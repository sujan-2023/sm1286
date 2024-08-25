package com.sm1286.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DateTimeUtils {
    public static boolean isDateInFormat(String date, String format) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate toLocalDate(String date, String format) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public static String toDateString(LocalDate date, String format) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static boolean isWeekendDate(LocalDate date) {
        if (Objects.isNull(date)) {
            throw new IllegalArgumentException("Date must not be null");
        }
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
