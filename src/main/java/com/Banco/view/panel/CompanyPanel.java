package com.Banco.view.panel;

import com.Banco.controller.BankController;
import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.view.base.BaseTablePanel;

import javax.swing.*;

/**
 * Panel to manage Investment Companies.
 * Allows viewing, adding, and deleting companies.
 */
public class CompanyPanel extends BaseTablePanel {
    private final BankController controller;

    public CompanyPanel(BankController controller) {
        super("Investment Companies Management", 
                new String[]{"Code", "Name", "Risk", "Reliability", "Return %"});
        this.controller = controller;

        JButton btnAdd = new JButton("Register Company");
        btnAdd.addActionListener(e -> addCompany());
        
        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.addActionListener(e -> deleteSelected());

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshData());

        btnPanel.add(btnAdd);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);
    }

    private void addCompany() {
        try {
            String code = JOptionPane.showInputDialog(this, "Enter Company Code (e.g., INV001):");
            if (code == null || code.trim().isEmpty()) return;

            String name = JOptionPane.showInputDialog(this, "Enter Company Name:");
            if (name == null || name.trim().isEmpty()) return;

            String returnStr = JOptionPane.showInputDialog(this, "Annual Return Rate (e.g. 0.08 = 8%):");
            if (returnStr == null) return;
            double returnRate = Double.parseDouble(returnStr);

            String[] risks = {"1 - Low", "2 - Medium", "3 - High"};
            String riskChoice = (String) JOptionPane.showInputDialog(this, "Select Risk Level:",
                    "Risk Level", JOptionPane.QUESTION_MESSAGE, null, risks, risks[0]);
            if (riskChoice == null) return;
            int riskLevel = Integer.parseInt(riskChoice.split(" - ")[0]);

            String relStr = JOptionPane.showInputDialog(this, "Reliability Index (0.0 to 1.0):");
            if (relStr == null) return;
            double reliability = Double.parseDouble(relStr);

            controller.companies().registerCompany(code, name, returnRate, riskLevel, reliability);
            refreshData();
            JOptionPane.showMessageDialog(this, "Company registered successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a company to delete.");
            return;
        }

        String code = (String) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete company " + code + "?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.companies().deleteCompany(code);
                refreshData();
                JOptionPane.showMessageDialog(this, "Company deleted successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void refreshData() {
        tableModel.setRowCount(0);
        Object[][] data = controller.reports().getCompaniesData();
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
