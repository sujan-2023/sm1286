package com.sm1286.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CommonUtils {

    public static double roundToCents(double amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static String currencyFormatter(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
        return decimalFormat.format(amount);
    }
}
