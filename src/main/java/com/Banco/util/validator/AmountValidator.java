package com.Banco.util.validator;

import com.Banco.exceptions.InvalidAmountException;

public final class AmountValidator extends BaseValidator{
    private AmountValidator(){}

    public static void requirePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be a positive number" + amount);

        }
    }
        public static void requireNonNegativeAmount(double amount) {
            if (amount < 0) {
                throw new InvalidAmountException(
                        String.format("Amount must not be negative, got: %.2f", amount)
                );
            }
        }
    public static void requireAmountInRange(double amount, double min, double max) {
        if (amount < min || amount > max) {
            throw new InvalidAmountException(
                    String.format("Amount must be between $%.2f and $%.2f, got: $%.2f",
                            min, max, amount)
            );
        }
    }

}
