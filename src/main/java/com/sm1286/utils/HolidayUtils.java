package com.sm1286.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

public class HolidayUtils {

    public static boolean isHoliday(LocalDate date) {
        if (Objects.isNull(date)) {
            return false;
        }
        return isIndependenceDay(date) || isObservedIndependenceDay(date) || isLaborDay(date);
    }

    static boolean isIndependenceDay(LocalDate date) {
        return date.getMonth() == Month.JULY && date.getDayOfMonth() == 4;
    }

    public static LocalDate getObservedIndependenceDay(int year) {
        final LocalDate independenceDay = LocalDate.of(year, 7, 4);
        final DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return independenceDay.minusDays(1); // Friday before
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            return independenceDay.plusDays(1); // Monday after
        } else {
            return null; // July 4th is not on a weekend
        }
    }

    static boolean isObservedIndependenceDay(LocalDate date) {
        final LocalDate observedIndependentDay = getObservedIndependenceDay(date.getYear());
        return date.equals(observedIndependentDay);
    }

    static boolean isLaborDay(LocalDate date) {
        if (date.getMonth() == Month.SEPTEMBER) {
            final LocalDate firstMondayInSeptember = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1)
                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
            return date.isEqual(firstMondayInSeptember);
        }
        return false;
    }
}
