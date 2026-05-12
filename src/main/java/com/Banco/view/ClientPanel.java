package com.Banco.view;

import com.Banco.controller.BankController;

import javax.swing.*;

public class ClientPanel extends BaseTablePanel {
    private final BankController controller;

    public ClientPanel(BankController controller) {
        super("Clients Management", new String[]{"ID", "Full Name", "Accounts Count"});
        this.controller = controller;

        JButton btnAdd = new JButton("Register Client");
        btnAdd.addActionListener(e -> addClient());
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshData());

        btnPanel.add(btnAdd);
        btnPanel.add(btnRefresh);
    }

    private void addClient() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter Client ID:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            int id = Integer.parseInt(idStr);

            String name = JOptionPane.showInputDialog(this, "Enter Full Name:");
            if (name == null || name.trim().isEmpty()) return;

            controller.clients().registerClient(id, name);
            refreshData();
            JOptionPane.showMessageDialog(this, "Client registered successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void refreshData() {
        tableModel.setRowCount(0);
        Object[][] data = controller.reports().getClientsData();
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
