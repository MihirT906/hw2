package controller;

import view.ExpenseTrackerView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.CategoryFilter;
import model.AmountFilter;

import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
  public boolean applyFilter(String filterType, String filterVal) {
    List<Transaction> filteredTransactions = new ArrayList<>(); 
    if (filterType.equals("Amount")) {
      try{
        double convAmountValue = Double.parseDouble(filterVal);
        if(!InputValidation.isValidAmount(convAmountValue)){
          return false;
        }

        AmountFilter amtFilter = new AmountFilter(convAmountValue);

        filteredTransactions = amtFilter.filter(model.getTransactions());
        System.out.println("amt= " + filteredTransactions);
      }catch(Exception e){
        return false;
      }

        

        

    } else if (filterType.equals("Category")) {
        CategoryFilter catFilter = new CategoryFilter(filterVal);

        if(!InputValidation.isValidCategory(filterVal)){
          return false;
        }

        filteredTransactions = catFilter.filter(model.getTransactions());
        System.out.println("cat= " + filteredTransactions);
    } else {
        // JOptionPane.showMessageDialog(view, "Invalid Filter Type");
        // System.out.println("Invalid Filter Type");
        return false;
    }

    view.highlightTransactions(filteredTransactions);
    return true;
}
}