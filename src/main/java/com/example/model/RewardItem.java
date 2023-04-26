package com.example.model;

public class RewardItem {
  private String month;
  private double point;

  public RewardItem(String month, double point) {
    this.month = month;
    this.point = point;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public double getPoint() {
    return point;
  }

  public void setPoint(double point) {
    this.point = point;
  }
}
