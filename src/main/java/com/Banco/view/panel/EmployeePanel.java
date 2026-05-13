package com.Banco.view.panel;

import com.Banco.controller.BankController;
import com.Banco.view.base.BaseTablePanel;

import javax.swing.*;


public class EmployeePanel extends BaseTablePanel {

    private final BankController controller;

    public EmployeePanel(BankController controller) {
        super("Employee Directory",
                new String[]{"ID", "Name", "Position", "Salary", "Years Worked", "Vacation Days"});
        this.controller = controller;

        JButton btnAdd     = new JButton("Register Employee");
        JButton btnRefresh = new JButton("Refresh");

        btnAdd.addActionListener(e     -> addEmployee());
        btnRefresh.addActionListener(e -> refreshData());

        btnPanel.add(btnAdd);
        btnPanel.add(btnRefresh);
    }

    // ── Register Employee ─────────────────────────────────────────────────────

    private void addEmployee() {
        try {
            String[] types = {"Cashier", "Supervisor", "Receptionist"};
            String type = (String) JOptionPane.showInputDialog(
                    this, "Select Employee Type:", "Employee Type",
                    JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            if (type == null) return;

            String idStr = JOptionPane.showInputDialog(this, "Enter Employee ID:");
            if (isBlankOrCancelled(idStr)) return;
            int id = Integer.parseInt(idStr.trim());

            String name = JOptionPane.showInputDialog(this, "Enter Full Name:");
            if (isBlankOrCancelled(name)) return;

            String salaryStr = JOptionPane.showInputDialog(this, "Enter Salary:");
            if (isBlankOrCancelled(salaryStr)) return;
            double salary = Double.parseDouble(salaryStr.trim());

            String yearsStr = JOptionPane.showInputDialog(this, "Enter Years Worked:");
            if (isBlankOrCancelled(yearsStr)) return;
            int years = Integer.parseInt(yearsStr.trim());

            // Controller factory methods — the view never imports concrete model classes
            int vacationDays = switch (type) {
                case "Cashier"       -> controller.employees()
                        .registerCashier(id, name.trim(), salary, years)
                        .calculateVacationDays();
                case "Supervisor"    -> controller.employees()
                        .registerSupervisor(id, name.trim(), salary, years)
                        .calculateVacationDays();
                case "Receptionist"  -> controller.employees()
                        .registerReceptionist(id, name.trim(), salary, years)
                        .calculateVacationDays();
                default -> throw new IllegalArgumentException("Unknown type: " + type);
            };

            refreshData();
            JOptionPane.showMessageDialog(this,
                    String.format("%s registered successfully.\nVacation days this year: %d",
                            type, vacationDays));
        } catch (NumberFormatException ex) {
            showError("Invalid number format: " + ex.getMessage());
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    // ── Refresh ───────────────────────────────────────────────────────────────

    @Override
    public void refreshData() {
        tableModel.setRowCount(0);
        for (Object[] row : controller.reports().getEmployeesData()) {
            tableModel.addRow(row);
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private boolean isBlankOrCancelled(String value) {
        return value == null || value.isBlank();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, "Error: " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}