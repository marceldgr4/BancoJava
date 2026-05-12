package com.Banco.view;

import com.Banco.controller.BankController;
import com.Banco.model.domain.Employee.Cashier;
import com.Banco.model.domain.Employee.Receptionist;
import com.Banco.model.domain.Employee.Supervisor;

import javax.swing.*;

public class EmployeePanel extends BaseTablePanel {
    private final BankController controller;

    public EmployeePanel(BankController controller) {
        super("Employee Directory", new String[]{"ID", "Name", "Position", "Salary", "Years", "Vacations"});
        this.controller = controller;

        JButton btnAdd = new JButton("Register Employee");
        btnAdd.addActionListener(e -> addEmployee());
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshData());

        btnPanel.add(btnAdd);
        btnPanel.add(btnRefresh);
    }

    private void addEmployee() {
        try {
            String[] options = {"Cashier", "Receptionist", "Supervisor"};
            String type = (String) JOptionPane.showInputDialog(this, "Select Employee Type:", "Type",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (type == null) return;

            String idStr = JOptionPane.showInputDialog(this, "Enter Employee ID:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            int id = Integer.parseInt(idStr);

            String name = JOptionPane.showInputDialog(this, "Enter Full Name:");
            if (name == null || name.trim().isEmpty()) return;

            String salaryStr = JOptionPane.showInputDialog(this, "Enter Salary:");
            if (salaryStr == null || salaryStr.trim().isEmpty()) return;
            double salary = Double.parseDouble(salaryStr);

            String yearsStr = JOptionPane.showInputDialog(this, "Enter Years Worked:");
            if (yearsStr == null || yearsStr.trim().isEmpty()) return;
            int years = Integer.parseInt(yearsStr);

            switch (type) {
                case "Cashier" -> controller.employees().registerEmployee(new Cashier(id, name, salary, years));
                case "Receptionist" -> controller.employees().registerEmployee(new Receptionist(id, name, salary, years));
                case "Supervisor" -> controller.employees().registerEmployee(new Supervisor(id, name, salary, years));
            }
            refreshData();
            JOptionPane.showMessageDialog(this, "Employee registered successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void refreshData() {
        tableModel.setRowCount(0);
        Object[][] data = controller.reports().getEmployeesData();
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
