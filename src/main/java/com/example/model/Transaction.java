package com.example.model;

public class Transaction {
  private String customerId;
  private String month;
  private double amount;

  public Transaction(String customerId, String month, double amount) {
    this.customerId = customerId;
    this.month = month;
    this.amount = amount;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
