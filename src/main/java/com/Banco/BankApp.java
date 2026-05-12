package com.Banco;

import com.Banco.controller.BankController;
import com.Banco.util.UIUtils;
import com.Banco.view.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BankApp extends JFrame {
    private final BankController controller;
    private JPanel mainContent;
    private CardLayout cardLayout;
    
    private ClientPanel clientsPanel;
    private EmployeePanel employeesPanel;
    private AccountPanel accountsPanel;
    private TransactionPanel transactionsPanel;
    private ReportPanel reportPanel;
    private StatusBar statusBar;
    private JPanel dashboardPanel;

    public BankApp(BankController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("BancoJava - Premium Banking Management");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(UIUtils.BACKGROUND);

        setLayout(new BorderLayout());

        // Sidebar
        add(createSidebar(), BorderLayout.WEST);

        // Status Bar
        statusBar = new StatusBar(controller);
        add(statusBar, BorderLayout.SOUTH);

        // Main Content Area
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);
        mainContent.setBackground(UIUtils.BACKGROUND);

        initPanels();

        add(mainContent, BorderLayout.CENTER);

        // Show Dashboard by default
        showPanel("DASHBOARD");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(240, 750));
        sidebar.setBackground(UIUtils.SIDEBAR_BG);
        sidebar.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel logo = new JLabel("BANCO JAVA");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(new EmptyBorder(0, 0, 30, 0));
        sidebar.add(logo);

        addSidebarButton(sidebar, "Dashboard", "DASHBOARD");
        addSidebarButton(sidebar, "Clients", "CLIENTS");
        addSidebarButton(sidebar, "Employees", "EMPLOYEES");
        addSidebarButton(sidebar, "Accounts", "ACCOUNTS");
        addSidebarButton(sidebar, "Transactions", "TRANSACTIONS");
        addSidebarButton(sidebar, "Reports", "REPORTS");

        sidebar.add(Box.createVerticalGlue());
        
        JButton btnExit = UIUtils.createSidebarButton("Log Out", null);
        btnExit.addActionListener(e -> System.exit(0));
        sidebar.add(btnExit);
        sidebar.add(Box.createVerticalStrut(20));

        return sidebar;
    }

    private void addSidebarButton(JPanel sidebar, String text, String panelName) {
        JButton btn = UIUtils.createSidebarButton(text, null);
        btn.addActionListener(e -> showPanel(panelName));
        sidebar.add(btn);
        sidebar.add(Box.createVerticalStrut(5));
    }

    private void initPanels() {
        // Dashboard
        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(UIUtils.BACKGROUND);
        dashboardPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel welcome = new JLabel("Welcome back, Administrator");
        welcome.setFont(UIUtils.FONT_TITLE);
        header.add(welcome, BorderLayout.WEST);
        dashboardPanel.add(header, BorderLayout.NORTH);

        JPanel cardsContainer = new JPanel(new GridLayout(1, 4, 20, 0));
        cardsContainer.setOpaque(false);
        cardsContainer.setBorder(new EmptyBorder(30, 0, 0, 0));
        
        dashboardPanel.add(cardsContainer, BorderLayout.CENTER);
        
        // Custom Panels
        clientsPanel = new ClientPanel(controller);
        employeesPanel = new EmployeePanel(controller);
        accountsPanel = new AccountPanel(controller);
        transactionsPanel = new TransactionPanel(controller);
        reportPanel = new ReportPanel(controller);

        mainContent.add(dashboardPanel, "DASHBOARD");
        mainContent.add(clientsPanel, "CLIENTS");
        mainContent.add(employeesPanel, "EMPLOYEES");
        mainContent.add(accountsPanel, "ACCOUNTS");
        mainContent.add(transactionsPanel, "TRANSACTIONS");
        mainContent.add(reportPanel, "REPORTS");
    }

    private void showPanel(String name) {
        if (name.equals("DASHBOARD")) {
            refreshDashboard();
        } else if (name.equals("CLIENTS")) {
            clientsPanel.refreshData();
        } else if (name.equals("EMPLOYEES")) {
            employeesPanel.refreshData();
        } else if (name.equals("ACCOUNTS")) {
            accountsPanel.refreshData();
        } else if (name.equals("TRANSACTIONS")) {
            transactionsPanel.refreshData();
        } else if (name.equals("REPORTS")) {
            reportPanel.refreshData();
        }
        statusBar.updateStatus();
        cardLayout.show(mainContent, name);
    }

    private void refreshDashboard() {
        JPanel cardsContainer = (JPanel) dashboardPanel.getComponent(1);
        cardsContainer.removeAll();
        
        cardsContainer.add(UIUtils.createCard("Total Clients", 
                String.valueOf(controller.reports().getClientsData().length), UIUtils.PRIMARY));
        cardsContainer.add(UIUtils.createCard("Active Accounts", 
                String.valueOf(controller.reports().getAccountsData().length), UIUtils.SECONDARY));
        cardsContainer.add(UIUtils.createCard("Staff Members", 
                String.valueOf(controller.reports().getEmployeesData().length), UIUtils.ACCENT));
        cardsContainer.add(UIUtils.createCard("Investments", 
                String.valueOf(controller.reports().getCompaniesData().length), new Color(46, 204, 113)));
        
        dashboardPanel.revalidate();
        dashboardPanel.repaint();
    }
}
