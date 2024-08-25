package com.sm1286.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class HolidayUtilsTest {
    @Test
    void testIsHolidayIndependenceDay() {
        LocalDate independenceDay = LocalDate.of(2024, Month.JULY, 4);
        assertTrue(HolidayUtils.isHoliday(independenceDay));
    }

    @Test
    void testIsHolidayObservedIndependenceDay() {
        // Independence Day on Saturday (July 4, 2026)
        LocalDate observedOnFriday = LocalDate.of(2026, Month.JULY, 3); // Observed date
        LocalDate actualIndependenceDay = LocalDate.of(2026, Month.JULY, 4);
        assertTrue(HolidayUtils.isHoliday(observedOnFriday));
        assertFalse(HolidayUtils.isObservedIndependenceDay(actualIndependenceDay));

        // Independence Day on Sunday (July 4, 2021)
        LocalDate observedOnMonday = LocalDate.of(2021, Month.JULY, 5); // Observed date
        LocalDate actualIndependenceDaySunday = LocalDate.of(2021, Month.JULY, 4);
        assertTrue(HolidayUtils.isHoliday(observedOnMonday));
        assertFalse(HolidayUtils.isObservedIndependenceDay(actualIndependenceDaySunday));
    }

    @Test
    void testIsHolidayLaborDay() {
        LocalDate laborDay = LocalDate.of(2024, Month.SEPTEMBER, 2); // Labor Day in 2024
        assertTrue(HolidayUtils.isHoliday(laborDay));

        // Date that is not Labor Day
        LocalDate notLaborDay = LocalDate.of(2024, Month.SEPTEMBER, 3);
        assertFalse(HolidayUtils.isHoliday(notLaborDay));
    }

    @Test
    void testIsHolidayNonHoliday() {
        LocalDate someRandomDate = LocalDate.of(2024, Month.AUGUST, 15);
        assertFalse(HolidayUtils.isHoliday(someRandomDate));
    }

    @Test
    void testIsHolidayNullDate() {
        assertFalse(HolidayUtils.isHoliday(null));
    }

    @Test
    void testIsIndependenceDay() {
        LocalDate julyFourth = LocalDate.of(2024, Month.JULY, 4);
        assertTrue(HolidayUtils.isIndependenceDay(julyFourth));

        LocalDate otherDate = LocalDate.of(2024, Month.JULY, 5);
        assertFalse(HolidayUtils.isIndependenceDay(otherDate));
    }

    @Test
    void testIsObservedIndependenceDay() {
        LocalDate observedOnFriday = LocalDate.of(2026, Month.JULY, 3);
        assertTrue(HolidayUtils.isObservedIndependenceDay(observedOnFriday));

        LocalDate observedOnMonday = LocalDate.of(2021, Month.JULY, 5);
        assertTrue(HolidayUtils.isObservedIndependenceDay(observedOnMonday));

        LocalDate actualIndependenceDay = LocalDate.of(2024, Month.JULY, 4);
        assertFalse(HolidayUtils.isObservedIndependenceDay(actualIndependenceDay));
    }

    @Test
    void testIsLaborDay() {
        LocalDate laborDay = LocalDate.of(2024, Month.SEPTEMBER, 2);
        assertTrue(HolidayUtils.isLaborDay(laborDay));

        LocalDate notLaborDay = LocalDate.of(2024, Month.SEPTEMBER, 3);
        assertFalse(HolidayUtils.isLaborDay(notLaborDay));
    }
}