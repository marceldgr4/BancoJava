package com.Banco.util.validator;

import com.Banco.exceptions.InvalidTransactionException;
import com.Banco.exceptions.ValidationException;
import com.Banco.util.Constants;

public final class AccountValidator extends  BaseValidator {
    private AccountValidator() {
        // Utility class, no debe instanciarse
    }

    /**
     * Valida que un número de cuenta sea válido.
     * Reglas:
     * - No nulo ni vacío
     * - Longitud mínima de 4 caracteres
     * - Solo alfanumérico
     */
    public static void validateAccountNumber(String accountNumber) {
        requireNonBlank(accountNumber, "Account number");

        if (accountNumber.length() < 4) {
            throw new ValidationException(
                    "Account number must be at least 4 characters long"
            );
        }

        if (!accountNumber.matches("^[A-Za-z0-9]+$")) {
            throw new ValidationException(
                    "Account number must contain only letters and numbers"
            );
        }
    }

    /**
     * Valida el depósito inicial para una cuenta de ahorro.
     * Requisito del .md: "el primer depósito que haga debe ser de $1,000 invariablemente"
     *
     * @param initialDeposit monto del primer depósito
     * @throws InvalidTransactionException si no es exactamente $1,000
     */
    public static void validateSavingsInitialDeposit(double initialDeposit) {
        if (initialDeposit != Constants.SAVINGS_MINIMUM_INITIAL) {
            throw new InvalidTransactionException(
                    String.format(
                            "First deposit in Savings Account must be exactly $%.2f, got: $%.2f",
                            Constants.SAVINGS_MINIMUM_INITIAL, initialDeposit
                    )
            );
        }
    }

    /**
     * Valida el depósito inicial para una cuenta de inversión.
     * Requisito: mínimo $25,000
     *
     * @param initialDeposit monto del primer depósito
     * @throws InvalidTransactionException si es menor a $25,000
     */
    public static void validateInvestmentInitialDeposit(double initialDeposit) {
        if (initialDeposit < Constants.INVESTMENT_MINIMUM_INITIAL) {
            throw new InvalidTransactionException(
                    String.format(
                            "Initial deposit in Investment Account must be at least $%.2f, got: $%.2f",
                            Constants.INVESTMENT_MINIMUM_INITIAL, initialDeposit
                    )
            );
        }
    }

    /**
     * Valida que un balance sea suficiente para un retiro en cuenta de ahorro.
     *
     * @param currentBalance balance actual
     * @param withdrawalAmount monto a retirar
     * @param accountNumber número de cuenta para mensajes de error
     * @throws InvalidTransactionException si el retiro violaría el mínimo
     */
    public static void validateSavingsWithdrawal(double currentBalance,
                                                 double withdrawalAmount,
                                                 String accountNumber) {
        double balanceAfter = currentBalance - withdrawalAmount;

        if (balanceAfter < Constants.SAVINGS_MINIMUM_BALANCE) {
            throw new InvalidTransactionException(
                    accountNumber,
                    "WITHDRAWAL",
                    withdrawalAmount,
                    String.format(
                            "Balance after withdrawal ($%.2f) would be below minimum required ($%.2f)",
                            balanceAfter, Constants.SAVINGS_MINIMUM_BALANCE
                    )
            );
        }
    }

    /**
     * Valida que un balance sea suficiente para un retiro en cuenta de inversión.
     *
     * @param currentBalance balance actual
     * @param withdrawalAmount monto a retirar
     * @param accountNumber número de cuenta
     * @param isCancelled si la cuenta está cancelada (permite retiro total)
     * @throws InvalidTransactionException si el retiro violaría el mínimo
     */
    public static void validateInvestmentWithdrawal(double currentBalance,
                                                    double withdrawalAmount,
                                                    String accountNumber,
                                                    boolean isCancelled) {
        if (isCancelled) {
            // Si está cancelada, puede retirar todo
            if (withdrawalAmount > currentBalance) {
                throw new InvalidTransactionException(
                        accountNumber,
                        "WITHDRAWAL",
                        withdrawalAmount,
                        "Cannot withdraw more than current balance"
                );
            }
            return;
        }

        double balanceAfter = currentBalance - withdrawalAmount;

        if (balanceAfter < Constants.INVESTMENT_MINIMUM_BALANCE) {
            throw new InvalidTransactionException(
                    accountNumber,
                    "WITHDRAWAL",
                    withdrawalAmount,
                    String.format(
                            "Balance after withdrawal ($%.2f) would be below minimum required ($%.2f). " +
                                    "Use fullWithdraw() to cancel and withdraw all funds.",
                            balanceAfter, Constants.INVESTMENT_MINIMUM_BALANCE
                    )
            );
        }
    }

    /**
     * Valida tasa de interés anual (debe estar entre 0 y 1).
     */
    public static void validateInterestRate(double rate) {
        requireInRange(rate, 0.0, 1.0, "Annual interest rate");
    }

    /**
     * Valida monto de transacción genérico (debe ser positivo).
     */
    public static void validateTransactionAmount(double amount) {
        requirePositive(amount, "Transaction amount");
    }
}

