package com.Banco;

import com.Banco.controller.BankController;
import javax.swing.*;
import java.awt.*;

public class BankApp extends JFrame {
    private final BankController controller;

    public BankApp(BankController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Sistema Bancario - Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        header.setPreferredSize(new Dimension(800, 60));
        JLabel title = new JLabel("Banking System Management");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Content Area
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        reportArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(reportArea);
        add(scrollPane, BorderLayout.CENTER);

        // Sidebar / Buttons
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1, 5, 5));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnClients = createStyledButton("Clients Report");
        JButton btnEmployees = createStyledButton("Employees Report");
        JButton btnAccounts = createStyledButton("Accounts Report");
        JButton btnSummary = createStyledButton("System Summary");
        JButton btnExit = createStyledButton("Exit");

        btnClients.addActionListener(e -> reportArea.setText(controller.reports().generateClientsReport()));
        btnEmployees.addActionListener(e -> reportArea.setText(controller.reports().generateEmployeesReport()));
        btnAccounts.addActionListener(e -> reportArea.setText(controller.reports().generateAccountsReport()));
        btnSummary.addActionListener(e -> reportArea.setText(controller.reports().generateSystemSummary()));
        btnExit.addActionListener(e -> System.exit(0));

        sidebar.add(btnClients);
        sidebar.add(btnEmployees);
        sidebar.add(btnAccounts);
        sidebar.add(btnSummary);
        sidebar.add(btnExit);

        add(sidebar, BorderLayout.WEST);

        // Initial view
        reportArea.setText(controller.reports().generateSystemSummary());
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return btn;
    }
}
