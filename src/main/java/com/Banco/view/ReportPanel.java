package com.Banco.view;

import com.Banco.controller.BankController;
import com.Banco.util.UIUtils;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    private final BankController controller;
    private JTextArea summaryArea;

    public ReportPanel(BankController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBackground(UIUtils.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("System Reports & Analytics");
        titleLabel.setFont(UIUtils.FONT_TITLE);
        add(titleLabel, BorderLayout.NORTH);

        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        summaryArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(summaryArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(UIUtils.BACKGROUND);

        JButton btnSystem = new JButton("System Summary");
        btnSystem.addActionListener(e -> summaryArea.setText(controller.reports().generateSystemSummary()));

        JButton btnClients = new JButton("Clients Report");
        btnClients.addActionListener(e -> summaryArea.setText(controller.reports().generateClientsReport()));

        JButton btnEmployees = new JButton("Employees Report");
        btnEmployees.addActionListener(e -> summaryArea.setText(controller.reports().generateEmployeesReport()));

        JButton btnAccounts = new JButton("Accounts Report");
        btnAccounts.addActionListener(e -> summaryArea.setText(controller.reports().generateAccountsReport()));

        btnPanel.add(btnSystem);
        btnPanel.add(btnClients);
        btnPanel.add(btnEmployees);
        btnPanel.add(btnAccounts);

        add(btnPanel, BorderLayout.SOUTH);
    }
    
    public void refreshData() {
        summaryArea.setText(controller.reports().generateSystemSummary());
    }
}
