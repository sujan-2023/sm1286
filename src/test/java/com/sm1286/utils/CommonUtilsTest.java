package com.sm1286.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

    @Test
    void testRoundToCents() {
        assertEquals(12.35, CommonUtils.roundToCents(12.346));
    }

    @Test
    void testCurrencyFormatter() {
        assertEquals("$12.34", CommonUtils.currencyFormatter(12.343));
    }
}