package com.expense_tracker.GUI;

import com.expense_tracker.dao.ExpenseDAO;
import com.expense_tracker.Model.Expense;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class IntroFrame extends JFrame {
    private JButton categoryButton;
    private JButton expenseButton;

    public IntroFrame() {
        setTitle("Intro Frame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponent();
        setUpActionListener();
    }

    private void initializeComponent() {
        setLayout(new GridBagLayout());
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        categoryButton = new JButton("CATEGORY");
        expenseButton = new JButton("EXPENSE");
        categoryButton.setPreferredSize(new Dimension(100, 100));
        expenseButton.setPreferredSize(new Dimension(100, 100));

        centerPanel.add(categoryButton);
        centerPanel.add(expenseButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(centerPanel, gbc);
    }

    private void setUpActionListener() {
        categoryButton.addActionListener(e -> new CategoryFrame().setVisible(true));
        expenseButton.addActionListener(e -> new ExpenseFrame().setVisible(true));
    }

    private class CategoryFrame extends JFrame {
        private JTextField categoryNameField;
        private JButton addButton, viewButton, deleteButton;
        private JTable categoryTable;
        private DefaultTableModel tableModel;

        public CategoryFrame() {
            setTitle("Category Frame");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            initializeComponent();
            setUpActionListener();
        }

        private void initializeComponent() {
            setLayout(new BorderLayout());

            JPanel northPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            northPanel.add(new JLabel("Category Name"), gbc);

            gbc.gridx = 1;
            categoryNameField = new JTextField();
            categoryNameField.setPreferredSize(new Dimension(150, 25));
            northPanel.add(categoryNameField, gbc);
            add(northPanel, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            addButton = new JButton("ADD");
            viewButton = new JButton("VIEW");
            deleteButton = new JButton("DELETE");

            centerPanel.add(addButton);
            centerPanel.add(viewButton);
            centerPanel.add(deleteButton);

            String[] columnNames = {"Category Name"};
            tableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            categoryTable = new JTable(tableModel);
            categoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            categoryTable.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int row = categoryTable.getSelectedRow();
                    if (row != -1) {
                        String selectedCategory = tableModel.getValueAt(row, 0).toString();
                        categoryNameField.setText(selectedCategory);
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(categoryTable);
            scrollPane.setPreferredSize(new Dimension(350, 300));

            centerPanel.add(new JPanel());
            centerPanel.add(scrollPane);

            add(centerPanel, BorderLayout.CENTER);
        }

        private void setUpActionListener() {
            addButton.addActionListener(e -> {
                // empty for now
            });

            viewButton.addActionListener(e -> {
                String curr = categoryNameField.getText().trim();
                if (!curr.isEmpty()) {
                    new DetailFrame(curr).setVisible(true);
                }
            });

            deleteButton.addActionListener(e -> {
                // empty for now
            });
        }

        private class DetailFrame extends JFrame {
            private JTable detailTable;
            private DefaultTableModel detailTableModel;
            private ExpenseDAO expensedao = new ExpenseDAO();
            private JTextField CatagoryField;
            private JTextField AmountField;
            private JTextArea DescriptionField;
            private JTextField DateField;
            private JButton addButton;
            private JButton deleteButton;
            private JButton updateButton;
            private JButton totalButton;

            public DetailFrame(String curr) {
                setTitle("Detail Frame");
                setSize(400, 600);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                initializeComponent();
                loadDetails(curr);
                setUpActionListener(curr);
            }

            private void initializeComponent() {
                setLayout(new BorderLayout());
                JPanel topPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.CENTER;
                topPanel.add(new JLabel("Category"), gbc);
                gbc.gridx = 1;
                CatagoryField = new JTextField();
                CatagoryField.setPreferredSize(new Dimension(150, 25));
                topPanel.add(CatagoryField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                topPanel.add(new JLabel("Amount"), gbc);
                gbc.gridx = 1;
                AmountField = new JTextField();
                AmountField.setPreferredSize(new Dimension(150, 25));
                topPanel.add(AmountField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                topPanel.add(new JLabel("Description"), gbc);
                gbc.gridx = 1;
                DescriptionField = new JTextArea();
                DescriptionField.setPreferredSize(new Dimension(150, 75));
                topPanel.add(DescriptionField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                topPanel.add(new JLabel("Date"), gbc);
                gbc.gridx = 1;
                DateField = new JTextField();
                DateField.setPreferredSize(new Dimension(150, 25));
                topPanel.add(DateField, gbc);

                add(topPanel, BorderLayout.NORTH);
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
                addButton = new JButton("ADD");
                deleteButton = new JButton("DELETE");
                updateButton = new JButton("UPDATE");
                totalButton = new JButton("TOTAL");
                buttonPanel.add(addButton);
                buttonPanel.add(deleteButton);
                buttonPanel.add(updateButton);
                buttonPanel.add(totalButton);
                add(buttonPanel, BorderLayout.SOUTH);

                String[] columnNames = {"ID", "Category", "Amount", "Description", "Date"};
                detailTableModel = new DefaultTableModel(columnNames, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                detailTable = new JTable(detailTableModel);
                detailTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                detailTable.getSelectionModel().addListSelectionListener(
                    (e)->{
                        if(!e.getValueIsAdjusting()){
                            loadTheDetails();
                        }
                    }
                    );

                JScrollPane scrollPane = new JScrollPane(detailTable);
                add(scrollPane, BorderLayout.CENTER);
            }

            private void loadTheDetails() {
                int row = detailTable.getSelectedRow();
                if (row != -1) {
                    String id = detailTableModel.getValueAt(row, 0).toString();
                    String category = detailTableModel.getValueAt(row, 1).toString();
                    String amount = detailTableModel.getValueAt(row, 2).toString();
                    String description = detailTableModel.getValueAt(row, 3).toString();
                    String date = detailTableModel.getValueAt(row, 4).toString();
                    CatagoryField.setText(category);
                    AmountField.setText(amount);
                    DescriptionField.setText(description);
                    DateField.setText(date);
                }
            }

            private void setUpActionListener(String curr) {

                this.addButton.addActionListener(
                    (e) -> {
                        Expense expense = new Expense();
                        expense.setCategory(CatagoryField.getText().trim());
                        expense.setAmount(Integer.parseInt(AmountField.getText().trim()));
                        expense.setDescription(DescriptionField.getText().trim());
                        // Assuming date is in yyyy-MM-dd format
                        expense.setDate(LocalDate.parse(DateField.getText().trim()));

                        try {
                            expensedao.addExpense(expense);
                            loadDetails(curr);
                            JOptionPane.showMessageDialog(this, "Expense added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error adding expense: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                );

                this.deleteButton.addActionListener(
                    (e) -> {
                        int row = detailTable.getSelectedRow();
                        if (row != -1) {
                            try {
                                int id = (Integer) detailTableModel.getValueAt(row, 0);
                                expensedao.deleteExpense(id);
                                loadDetails(curr);
                                JOptionPane.showMessageDialog(this, "Record deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(this, "Error deleting record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                );

                this.updateButton.addActionListener(
                    (e) -> {
                        int row = detailTable.getSelectedRow();
                        if (row != -1) {
                            Expense expense = new Expense();
                            expense.setId((Integer) detailTableModel.getValueAt(row, 0));
                            expense.setCategory(CatagoryField.getText().trim());
                            expense.setAmount(Integer.parseInt(AmountField.getText().trim()));
                            expense.setDescription(DescriptionField.getText().trim());
                            expense.setDate(LocalDate.parse(DateField.getText().trim()));

                            try {
                                expensedao.updateExpense(expense);
                                loadDetails(curr);
                                JOptionPane.showMessageDialog(this, "Record updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(this, "Error updating record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                );

                this.totalButton.addActionListener(
                    (e) -> {
                        int total = 0;
                        for (int i = 0; i < detailTableModel.getRowCount(); i++) {
                            total += (Integer) detailTableModel.getValueAt(i, 2);
                        }
                        JOptionPane.showMessageDialog(this, "Total Expense: " + total, "Total", JOptionPane.INFORMATION_MESSAGE);
                    }
                );
}


            private void updateTable(List<Expense> expenses) {
                detailTableModel.setRowCount(0);
                for (Expense exp : expenses) {
                    Object[] rowData = {
                        exp.getId(),
                        exp.getCategory(),
                        exp.getAmount(),
                        exp.getDescription(),
                        exp.getDate()
                    };
                    detailTableModel.addRow(rowData);
                }
            }

            private void loadDetails(String curr) {
                try {
                    List<Expense> expenses = expensedao.getCategory(curr);
                    updateTable(expenses);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error fetching details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class ExpenseFrame extends JFrame {
        public ExpenseFrame() {
            setTitle("Expense Frame");
            setSize(400, 600);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            initializeComponent();
        }

        private void initializeComponent() {
            // empty for now
        }
    }
}
