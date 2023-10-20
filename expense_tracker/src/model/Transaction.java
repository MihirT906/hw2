package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class Transaction {

  private final double amount;
  private final String category;
  private final String timestamp;

  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  public double getAmount() {
    return amount;
  }

  // public void setAmount(double amount) {
  //   this.amount = amount;
  // }

  public String getCategory() {
    return category;
  }

  // public void setCategory(String category) {
  //   this.category = category; 
  // }
  
  public String getTimestamp() {
    return timestamp;
  }

  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(category, that.category);
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(amount, category);
    // }
}