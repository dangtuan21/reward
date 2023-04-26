package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Reward {
  private String customerId;
  private double totalPoint;

  private List<RewardItem> rewardItems;

  public Reward(String customerId, double totalPoint) {
    this.customerId = customerId;
    this.totalPoint = totalPoint;
    rewardItems = new ArrayList<>();
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public double getTotalPoint() {
    return totalPoint;
  }

  public void setTotalPoint(double totalPoint) {
    this.totalPoint = totalPoint;
  }

  public List<RewardItem> getRewardItems() {
    return rewardItems;
  }

  public void setRewardItems(List<RewardItem> rewardItems) {
    this.rewardItems = rewardItems;
  }

  @Override
  public String toString() {
    return "Reward{"
        + "customerId='"
        + customerId
        + '\''
        + ", totalPoint="
        + totalPoint
        + ", rewardItems="
        + rewardItems.size()
        + '}';
  }
}
