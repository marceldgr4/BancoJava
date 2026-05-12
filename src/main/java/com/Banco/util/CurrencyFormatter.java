package com.Banco.util;

import java.text.NumberFormat;

public final class CurrencyFormatter {

    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);

    private CurrencyFormatter() {}

    public static String format(double amount) {
        return FORMATTER.format(amount);
    }

    public static String formatDiff(double amount) {
        String prefix = amount >= 0 ? "+" : "";
        return prefix + FORMATTER.format(amount);
    }
}
