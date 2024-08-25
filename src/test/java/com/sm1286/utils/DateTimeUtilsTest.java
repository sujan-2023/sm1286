package com.sm1286.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {
    @Test
    void testIsDateInFormatValidFormat() {
        assertTrue(DateTimeUtils.isDateInFormat("2024-08-25", "yyyy-MM-dd"));
        assertTrue(DateTimeUtils.isDateInFormat("08/25/2024", "MM/dd/yyyy"));
        assertFalse(DateTimeUtils.isDateInFormat("2024-08-25", "MM/dd/yyyy"));
    }

    @Test
    void testIsDateInFormatInvalidFormat() {
        assertFalse(DateTimeUtils.isDateInFormat("25/08/2024", "yyyy-MM-dd"));
        assertFalse(DateTimeUtils.isDateInFormat("2024-08-25", "dd/MM/yyyy"));
    }

    @Test
    void testToLocalDateValidDate() {
        LocalDate date = DateTimeUtils.toLocalDate("2024-08-25", "yyyy-MM-dd");
        assertEquals(LocalDate.of(2024, 8, 25), date);
    }

    @Test
    void testToLocalDateInvalidDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateTimeUtils.toLocalDate("25/08/2024", "yyyy-MM-dd");
        });
        assertEquals("Invalid date", exception.getMessage());
    }

    @Test
    void testToDateString() {
        LocalDate date = LocalDate.of(2024, 8, 25);
        assertEquals("2024-08-25", DateTimeUtils.toDateString(date, "yyyy-MM-dd"));
        assertEquals("08/25/2024", DateTimeUtils.toDateString(date, "MM/dd/yyyy"));
    }

    @Test
    void testIsWeekendDateValidDates() {
        LocalDate saturday = LocalDate.of(2024, 8, 24);
        LocalDate sunday = LocalDate.of(2024, 8, 25);
        LocalDate monday = LocalDate.of(2024, 8, 26);

        assertTrue(DateTimeUtils.isWeekendDate(saturday));
        assertTrue(DateTimeUtils.isWeekendDate(sunday));
        assertFalse(DateTimeUtils.isWeekendDate(monday));
    }

    @Test
    void testIsWeekendDateNullDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateTimeUtils.isWeekendDate(null);
        });
        assertEquals("Date must not be null", exception.getMessage());
    }
}