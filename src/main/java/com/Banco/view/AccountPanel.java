package com.Banco.view;

import com.Banco.controller.BankController;
import com.Banco.model.domain.Account.SavingsAccount;
import com.Banco.model.domain.Person.Client;

import javax.swing.*;

public class AccountPanel extends BaseTablePanel {
    private final BankController controller;

    public AccountPanel(BankController controller) {
        super("Bank Accounts & Operations", new String[]{"Account Number", "Owner", "Type", "Balance", "Details"});
        this.controller = controller;

        JButton btnOpen = new JButton("Open Savings Account");
        btnOpen.addActionListener(e -> openAccount());
        
        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.addActionListener(e -> deposit());

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.addActionListener(e -> withdraw());

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshData());

        btnPanel.add(btnOpen);
        btnPanel.add(btnDeposit);
        btnPanel.add(btnWithdraw);
        btnPanel.add(btnRefresh);
    }

    private void openAccount() {
        try {
            String clientIdStr = JOptionPane.showInputDialog(this, "Enter Client ID:");
            if (clientIdStr == null || clientIdStr.trim().isEmpty()) return;
            int clientId = Integer.parseInt(clientIdStr);
            
            Client client = controller.clients().getClientById(clientId);
            if (client == null) {
                JOptionPane.showMessageDialog(this, "Client not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String accNum = JOptionPane.showInputDialog(this, "Enter New Account Number:");
            if (accNum == null || accNum.trim().isEmpty()) return;

            String initBalStr = JOptionPane.showInputDialog(this, "Enter Initial Balance:");
            if (initBalStr == null || initBalStr.trim().isEmpty()) return;
            double initBal = Double.parseDouble(initBalStr);

            String rateStr = JOptionPane.showInputDialog(this, "Enter Annual Interest Rate (0.0 to 1.0):");
            if (rateStr == null || rateStr.trim().isEmpty()) return;
            double rate = Double.parseDouble(rateStr);

            SavingsAccount acc = new SavingsAccount(accNum, client, initBal, rate);
            controller.accounts().openAccount(clientId, acc);

            refreshData();
            JOptionPane.showMessageDialog(this, "Account opened successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deposit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an account from the table.");
            return;
        }
        String accNum = (String) tableModel.getValueAt(selectedRow, 0);
        String amountStr = JOptionPane.showInputDialog(this, "Enter Deposit Amount:");
        if (amountStr != null && !amountStr.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                controller.accounts().deposit(accNum, amount);
                refreshData();
                JOptionPane.showMessageDialog(this, "Deposit successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void withdraw() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an account from the table.");
            return;
        }
        String accNum = (String) tableModel.getValueAt(selectedRow, 0);
        String amountStr = JOptionPane.showInputDialog(this, "Enter Withdrawal Amount:");
        if (amountStr != null && !amountStr.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                controller.accounts().withdraw(accNum, amount);
                refreshData();
                JOptionPane.showMessageDialog(this, "Withdrawal successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void refreshData() {
        tableModel.setRowCount(0);
        Object[][] data = controller.reports().getAccountsData();
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
