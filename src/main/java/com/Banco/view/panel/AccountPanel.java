package com.Banco.view.panel;

import com.Banco.controller.BankController;
import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.view.base.BaseTablePanel;

import javax.swing.*;
import java.util.List;

/**
 * Panel de cuentas bancarias.
 *
 * Operaciones:
 *  - Abrir cuenta de ahorro  (depósito inicial fijo $1,000)
 *  - Abrir cuenta de inversión (mínimo $25,000 + empresa inversora)
 *  - Depósito / Retiro con validación de propietario
 *  - Intereses mensuales: a UNA cuenta seleccionada O a TODAS
 *
 * MVC: no importa ninguna clase concreta del modelo.
 */
public class AccountPanel extends BaseTablePanel {

    private final BankController controller;

    public AccountPanel(BankController controller) {
        super("Bank Accounts & Operations",
                new String[]{"Account Number", "Owner", "Type", "Balance", "Details"});
        this.controller = controller;

        JButton btnOpenSavings    = new JButton("Open Savings Account");
        JButton btnOpenInvestment = new JButton("Open Investment Account");
        JButton btnDeposit        = new JButton("Deposit");
        JButton btnWithdraw       = new JButton("Withdraw");
        JButton btnInterest       = new JButton("Apply Monthly Interest");
        JButton btnRefresh        = new JButton("Refresh");

        btnOpenSavings.addActionListener(e    -> openSavingsAccount());
        btnOpenInvestment.addActionListener(e -> openInvestmentAccount());
        btnDeposit.addActionListener(e        -> deposit());
        btnWithdraw.addActionListener(e       -> withdraw());
        btnInterest.addActionListener(e       -> chooseInterestScope());
        btnRefresh.addActionListener(e        -> refreshData());

        btnPanel.add(btnOpenSavings);
        btnPanel.add(btnOpenInvestment);
        btnPanel.add(btnDeposit);
        btnPanel.add(btnWithdraw);
        btnPanel.add(btnInterest);
        btnPanel.add(btnRefresh);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Open Savings Account
    // ─────────────────────────────────────────────────────────────────────────

    private void openSavingsAccount() {
        try {
            String clientIdStr = prompt("Enter Client ID:");
            if (clientIdStr == null) return;
            int clientId = Integer.parseInt(clientIdStr);

            String accNum = prompt("Enter Account Number:");
            if (accNum == null) return;

            String rateStr = prompt("Annual Interest Rate (e.g. 0.05 = 5%):");
            if (rateStr == null) return;
            double rate = Double.parseDouble(rateStr);

            // Regla de negocio: primer depósito siempre $1,000
            controller.accounts().openSavingsAccount(clientId, accNum, 1000.0, rate);
            refreshData();
            JOptionPane.showMessageDialog(this,
                    "Savings account opened.\nInitial deposit: $1,000.00 (fixed by policy).");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Open Investment Account
    // ─────────────────────────────────────────────────────────────────────────

    private void openInvestmentAccount() {
        try {
            List<InvestmentCompany> companies = controller.getAllCompanies();
            if (companies.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No investment companies registered in the system.",
                        "No Companies", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String clientIdStr = prompt("Enter Client ID:");
            if (clientIdStr == null) return;
            int clientId = Integer.parseInt(clientIdStr);

            String accNum = prompt("Enter Account Number:");
            if (accNum == null) return;

            String initBalStr = prompt("Initial Balance (minimum $25,000):");
            if (initBalStr == null) return;
            double initBal = Double.parseDouble(initBalStr);

            String[] options = companies.stream()
                    .map(c -> String.format("%s – %s | Risk: %s | Return: %.1f%%",
                            c.getCode(), c.getName(),
                            c.getRiskDescription(),
                            c.getReturnPercentage() * 100))
                    .toArray(String[]::new);

            String selected = (String) JOptionPane.showInputDialog(
                    this, "Select Investment Company:", "Choose Company",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (selected == null) return;

            String code = selected.split(" – ")[0];
            InvestmentCompany company = companies.stream()
                    .filter(c -> c.getCode().equals(code))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Company not found: " + code));

            controller.accounts().openInvestmentAccount(clientId, accNum, initBal, company);
            refreshData();
            JOptionPane.showMessageDialog(this, "Investment account opened successfully.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Deposit
    // ─────────────────────────────────────────────────────────────────────────

    private void deposit() {
        try {
            String accNum = selectedAccountNumber();
            if (accNum == null) return;

            String clientIdStr = prompt("Enter your Client ID (ownership check):");
            if (clientIdStr == null) return;
            int clientId = Integer.parseInt(clientIdStr);

            String amountStr = prompt("Enter Deposit Amount:");
            if (amountStr == null) return;
            double amount = Double.parseDouble(amountStr);

            controller.accounts().deposit(clientId, accNum, amount);
            refreshData();
            JOptionPane.showMessageDialog(this, "Deposit successful.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Withdraw
    // ─────────────────────────────────────────────────────────────────────────

    private void withdraw() {
        try {
            String accNum = selectedAccountNumber();
            if (accNum == null) return;
            String accountType = (String) tableModel.getValueAt(table.getSelectedRow(), 2);

            String clientIdStr = prompt("Enter your Client ID (ownership check):");
            if (clientIdStr == null) return;
            int clientId = Integer.parseInt(clientIdStr);

            // Cuenta de inversión: ofrecer retiro total (cancelación)
            if ("Investment Account".equals(accountType)) {
                int opt = JOptionPane.showConfirmDialog(this,
                        "Cancel account and withdraw ALL funds?\n" +
                                "Select NO for a partial withdrawal.",
                        "Investment Account Withdrawal", JOptionPane.YES_NO_CANCEL_OPTION);
                if (opt == JOptionPane.CANCEL_OPTION) return;
                if (opt == JOptionPane.YES_OPTION) {
                    double total = controller.accounts().fullWithdraw(accNum);
                    refreshData();
                    JOptionPane.showMessageDialog(this,
                            String.format("Account cancelled. Total withdrawn: $%.2f", total));
                    return;
                }
            }

            String amountStr = prompt("Enter Withdrawal Amount:");
            if (amountStr == null) return;
            double amount = Double.parseDouble(amountStr);

            controller.accounts().withdraw(clientId, accNum, amount);
            refreshData();
            JOptionPane.showMessageDialog(this, "Withdrawal successful.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Apply Monthly Interest  – con selección de alcance
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Muestra dos opciones:
     *   1. Aplicar solo a la cuenta de ahorro seleccionada en la tabla.
     *   2. Aplicar a TODAS las cuentas de ahorro del sistema.
     */
    private void chooseInterestScope() {
        String[] scopeOptions = {
                "Selected savings account only",
                "ALL savings accounts"
        };
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Apply monthly interest to:",
                "Monthly Interest",
                JOptionPane.QUESTION_MESSAGE,
                null,
                scopeOptions,
                scopeOptions[0]);

        if (choice == null) return;

        if (choice.equals(scopeOptions[0])) {
            applyInterestToSelected();
        } else {
            applyInterestToAll();
        }
    }

    private void applyInterestToSelected() {
        String accNum = selectedAccountNumber();
        if (accNum == null) return;
        try {
            controller.accounts().applyMonthlyInterestToAccount(accNum);
            refreshData();
            JOptionPane.showMessageDialog(this,
                    "Monthly interest applied to account: " + accNum);
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void applyInterestToAll() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apply monthly interest to ALL savings accounts?",
                "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            controller.accounts().applyMonthlyInterest();
            refreshData();
            JOptionPane.showMessageDialog(this,
                    "Monthly interest applied to all savings accounts.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Helpers
    // ─────────────────────────────────────────────────────────────────────────

    @Override
    public void refreshData() {
        tableModel.setRowCount(0);
        for (Object[] row : controller.reports().getAccountsData()) {
            tableModel.addRow(row);
        }
    }

    private String selectedAccountNumber() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select an account from the table first.");
            return null;
        }
        return (String) tableModel.getValueAt(row, 0);
    }

    private String prompt(String message) {
        String value = JOptionPane.showInputDialog(this, message);
        return (value == null || value.isBlank()) ? null : value.trim();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                "Error: " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}