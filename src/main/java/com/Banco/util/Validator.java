package com.Banco.util;

import com.Banco.execption.InvalidAmountException;

public final class Validator {
    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(fieldName + " must not be blank.");
    }

    public static void requirePositiveAmount(double amount) {
        if (amount <= 0)
            throw new InvalidAmountException(amount);
    }

    public static void requireNonNegative(double value, String fieldName) {
        if (value < 0)
            throw new IllegalArgumentException(fieldName + " must not be negative.");
    }

    public static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max)
            throw new IllegalArgumentException(String.format(
                    "%s must be between %d and %d, got %d.", fieldName, min, max, value
            ));
    }

    public static void requireNonNull(Object value, String fieldName) {
        if (value == null)
            throw new IllegalArgumentException(fieldName + " must not be null.");
    }
}
