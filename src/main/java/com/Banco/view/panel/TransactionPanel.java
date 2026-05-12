package com.Banco.view.panel;

import com.Banco.controller.BankController;
import com.Banco.model.Transaction.Transaction;
import com.Banco.view.base.BaseTablePanel;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionPanel extends BaseTablePanel {
    private final BankController controller;

    public TransactionPanel(BankController controller) {
        super("Transactions History", new String[]{"Date", "Type", "Amount", "Balance After"});
        this.controller = controller;

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshData());
        btnPanel.add(btnRefresh);
    }

    @Override
    public void refreshData() {
        tableModel.setRowCount(0);
        List<Transaction> transactions = controller.transactions().getAllTransactions();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Transaction t : transactions) {
            tableModel.addRow(new Object[]{
                    t.getTimestamp().format(formatter),
                    t.getType(),
                    String.format("$%.2f", t.getAmount()),
                    String.format("$%.2f", t.getBalanceAfter())
            });
        }
    }
}
