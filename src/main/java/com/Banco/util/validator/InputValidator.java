package com.Banco.util.validator;

import com.Banco.exceptions.InvalidAmountException;
import com.Banco.exceptions.ValidationException;

public final class InputValidator extends BaseValidator {

    private InputValidator() {
    }

    /*
     * Parsea un String a int y valida que sea positivo.
     * @throws ValidationException si no es numérico o no es positivo
     */

        public static int parsePositiveInt(String input, String fieldName) {
            requireNonBlank(input, fieldName);

            try {
                int value = Integer.parseInt(input.trim());
                requirePositive(value, fieldName);
                return value;
            } catch (NumberFormatException e) {
                throw new ValidationException(
                        String.format("%s must be a valid integer, got: '%s'", fieldName, input)
                );
            }
        }

        /*
         * Parsea un String a double y valida que sea positivo.
         * @throws InvalidAmountException si no es numérico o no es positivo
         */
        public static double parsePositiveDouble(String input, String fieldName) {
            requireNonBlank(input, fieldName);

            try {
                double value = Double.parseDouble(input.trim());
                requirePositive(value, fieldName);
                return value;
            } catch (NumberFormatException e) {
                throw new InvalidAmountException(
                        String.format("%s must be a valid number, got: '%s'", fieldName, input)
                );
            }
        }

        /*
         * Parsea un String a double sin validar si es positivo.
         * @throws ValidationException si no es numérico
         */
        public static double parseDouble(String input, String fieldName) {
            requireNonBlank(input, fieldName);

            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                throw new ValidationException(
                        String.format("%s must be a valid number, got: '%s'", fieldName, input)
                );
            }
        }

        /*
         * Parsea String a double, retorna valor por defecto si falla.
         * Útil para campos opcionales.
         */
        public static double parseDoubleOrDefault(String input, double defaultValue) {
            if (input == null || input.trim().isEmpty()) {
                return defaultValue;
            }

            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

        //Sanitiza un String (trim y null-safe).

        public static String sanitize(String input) {
            return input == null ? "" : input.trim();
        }

        // Valida que un String de nombre sea válido.

        public static void validateName(String name) {
            requireNonBlank(name, "Name");

            if (name.length() < 2) {
                throw new ValidationException("Name must be at least 2 characters long");
            }

            if (!name.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                throw new ValidationException("Name must contain only letters and spaces");
            }
        }
    }
