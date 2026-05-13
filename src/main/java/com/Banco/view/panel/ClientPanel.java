package com.Banco.view.panel;

import com.Banco.controller.BankController;
import com.Banco.view.base.BaseTablePanel;

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
            String name = JOptionPane.showInputDialog(this, "Enter Full Name:");
            if (name == null || name.trim().isEmpty()) return;

            com.Banco.model.domain.Person.Client newClient = controller.clients().registerClient(name);
            refreshData();
            JOptionPane.showMessageDialog(this, "Client registered successfully with ID: " + newClient.getId());
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
