package com.Banco.util;

public final class Constants {

    private Constants() {}
    // ── SavingsAccount ────────────────────────────────────────────────────────
    public static final double SAVINGS_MINIMUM_INITIAL  = 1000.0;
    public static final double SAVINGS_MINIMUM_BALANCE  = 500.0;

    // ── InvestmentAccount ─────────────────────────────────────────────────────
    public static final double INVESTMENT_MINIMUM_INITIAL  = 25000.0;
    public static final double INVESTMENT_MINIMUM_BALANCE  = 10000.0;

    // ── Employee vacation ─────────────────────────────────────────────────────
    public static final int VACATION_BASE_DAYS      = 5;
    public static final int VACATION_INCREMENT      = 2;
    public static final int VACATION_MAX_DAYS       = 20;

    // ── InvestmentCompany ─────────────────────────────────────────────────────
    public static final int RISK_LEVEL_MIN = 1;
    public static final int RISK_LEVEL_MAX = 5;
}
