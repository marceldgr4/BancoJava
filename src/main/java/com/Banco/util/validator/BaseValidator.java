package com.Banco.util.validator;

import com.Banco.exceptions.ValidationException;


public abstract class BaseValidator {

    /**
     * Valida que un String no sea nulo ni vacío.
     * @throws ValidationException si la validación falla
     */
    protected static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " must not be blank");
        }
    }

    /**
     * Valida que un objeto no sea nulo.
     * @throws ValidationException si la validación falla
     */
    public static void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new ValidationException(fieldName + " must not be null");
        }
    }

    /**
     * Valida que un número sea positivo (> 0).
     * @throws ValidationException si la validación falla
     */
    protected static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException(
                    String.format("%s must be positive, got: %.2f", fieldName, value)
            );
        }
    }

    /**
     * Valida que un número no sea negativo (>= 0).
     * @throws ValidationException si la validación falla
     */
    protected static void requireNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new ValidationException(
                    String.format("%s must not be negative, got: %.2f", fieldName, value)
            );
        }
    }

    /**
     * Valida que un número entero esté en un rango específico.
     * @throws ValidationException si la validación falla
     */
    protected static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new ValidationException(
                    String.format("%s must be between %d and %d, got: %d",
                            fieldName, min, max, value)
            );
        }
    }

    /**
     * Valida que un número double esté en un rango específico.
     * @throws ValidationException si la validación falla
     */
    protected static void requireInRange(double value, double min, double max, String fieldName) {
        if (value < min || value > max) {
            throw new ValidationException(
                    String.format("%s must be between %.2f and %.2f, got: %.2f",
                            fieldName, min, max, value)
            );
        }
    }

    /**
     * Valida que un entero sea positivo (> 0).
     * @throws ValidationException si la validación falla
     */
    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException(
                    String.format("%s must be positive, got: %d", fieldName, value)
            );
        }
    }

    /**
     * Valida que un entero no sea negativo (>= 0).
     * @throws ValidationException si la validación falla
     */
    protected static void requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new ValidationException(
                    String.format("%s must not be negative, got: %d", fieldName, value)
            );
        }
    }
}