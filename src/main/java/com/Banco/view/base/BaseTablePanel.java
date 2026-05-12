package com.Banco.view.base;

import com.Banco.util.UIUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class BaseTablePanel extends JPanel {
    protected final JTable table;
    protected final DefaultTableModel tableModel;
    protected final JLabel titleLabel;
    protected final JPanel btnPanel;

    public BaseTablePanel(String title, String[] columns) {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIUtils.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        titleLabel = new JLabel(title);
        titleLabel.setFont(UIUtils.FONT_TITLE);
        titleLabel.setForeground(UIUtils.TEXT_PRIMARY);
        add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(UIUtils.FONT_NORMAL);
        table.getTableHeader().setFont(UIUtils.FONT_HEADER);
        table.getTableHeader().setBackground(UIUtils.PRIMARY);
        table.getTableHeader().setForeground(Color.BLACK);
        table.setSelectionBackground(UIUtils.SECONDARY);
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(230, 230, 230));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        add(scrollPane, BorderLayout.CENTER);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(UIUtils.BACKGROUND);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void updateData(Object[][] data) {
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    /**
     * Subclasses can override this to implement data refresh logic.
     */
    public void refreshData() {
        // Default implementation does nothing
    }
}
