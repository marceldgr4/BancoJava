package com.Banco.view.componet;

import com.Banco.controller.BankController;
import com.Banco.util.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusBar extends JPanel {
    private final BankController controller;
    private final JLabel statusLabel;

    public StatusBar(BankController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241));
        setBorder(new EmptyBorder(5, 10, 5, 10));

        statusLabel = new JLabel("System Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(UIUtils.TEXT_PRIMARY);

        add(statusLabel, BorderLayout.WEST);
    }

    public void updateStatus() {
        int clients = controller.clients().getTotalClients();
        int accounts = controller.reports().getAccountsData().length;
        int tx = controller.transactions().getAllTransactions().size();
        
        statusLabel.setText(String.format("System Status: %d Clients | %d Accounts | %d Transactions", clients, accounts, tx));
    }
}
