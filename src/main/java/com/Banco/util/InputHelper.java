package com.Banco.util;

import com.Banco.execption.InvalidAmountException;

public final class InputHelper {
    private InputHelper() {}

    /**
     * Parsea un String a double.
     * @throws InvalidAmountException si el valor no es numérico o no es positivo
     */
    public static double parsePositiveAmount(String input) {
        if (input == null || input.isBlank())
            throw new InvalidAmountException("Amount input must not be blank.");
        try {
            double value = Double.parseDouble(input.trim());
            if (value <= 0)
                throw new InvalidAmountException(value);
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidAmountException("Invalid numeric value: '" + input + "'");
        }
    }

    /**
     * Parsea un String a double sin validar si es positivo.
     * @throws IllegalArgumentException si el valor no es numérico
     */
    public static double parseDouble(String input) {
        if (input == null || input.isBlank())
            throw new IllegalArgumentException("Input must not be blank.");
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric value: '" + input + "'");
        }
    }

    /**
     * Intenta parsear, retorna defaultValue si falla.
     * Útil para valores opcionales en formularios.
     */
    public static double parseDoubleOrDefault(String input, double defaultValue) {
        try {
            return Double.parseDouble(input.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String sanitize(String input) {
        return input == null ? "" : input.trim();
    }
}
