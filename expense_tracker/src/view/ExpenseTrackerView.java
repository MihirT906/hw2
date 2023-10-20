package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;

  private JComboBox<String> filterType; 
  private JTextField filterValueField; 
  private JButton filterButton;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(1000, 500); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model){@Override
      public boolean isCellEditable(int row, int column) {
          // Make the entire table non-editable
          return false;
      }};
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    //add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(1000, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // Adding the UI components for filter type and value
        filterType = new JComboBox<>(new String[]{"Amount", "Category"});
        filterButton = new JButton("Filter");

        filterValueField = new JTextField(10);

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter using:"));
        filterPanel.add(filterType);
        filterPanel.add(new JLabel("Value:"));
        filterPanel.add(filterValueField);
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.SOUTH);

  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  
   
  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public JButton getFilterButton() {
    return filterButton;
}

public JComboBox<String> getFilterTypeDesc() {
    return filterType;
}

public JTextField getFilterValueField() {
    return filterValueField;
}


private Transaction getRowTransaction(int row) {
  DefaultTableModel tableModel = (DefaultTableModel) transactionsTable.getModel();
  
  Object amt = tableModel.getValueAt(row, 1);
  Object cat = tableModel.getValueAt(row, 2);

  if (amt != null && cat != null) {
      String category = (String) cat;
      double amount = (Double) amt;
      Transaction new_t= new Transaction(amount, category);
      return new_t;
  }

  return null; 
}


public void highlightTransactions(List<Transaction> filteredTransactions) {
    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Transaction currentTransaction = getRowTransaction(row);
            boolean toHighlight = filteredTransactions.contains(currentTransaction);

            if (toHighlight) {
                c.setBackground(new Color(173, 255, 168)); 
            } else {
                c.setBackground(table.getBackground()); 
            }

            return c;
        }
    });
    transactionsTable.repaint();
}




}
