package com.example.service;

import com.example.controller.RewardController;
import com.example.model.Reward;
import com.example.model.RewardItem;
import com.example.model.Transaction;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RewardService {
  private static final Logger LOG = LoggerFactory.getLogger(RewardController.class);

  private List<Transaction> dumpDB = new ArrayList<Transaction>();

  private void initDB() {
    this.dumpDB.add(new Transaction("cust1", "1", 10));
    this.dumpDB.add(new Transaction("cust1", "2", 60));
    this.dumpDB.add(new Transaction("cust2", "1", 80));
    this.dumpDB.add(new Transaction("cust2", "2", 40));
    this.dumpDB.add(new Transaction("cust3", "1", 30));
    this.dumpDB.add(new Transaction("cust4", "1", 70));
    this.dumpDB.add(new Transaction("cust4", "2", 110));
    this.dumpDB.add(new Transaction("cust4", "3", 30));
  }

  public RewardService() {
    initDB();
  }

  public List<Transaction> getTransactions() {
    return dumpDB;
  }

  public List<Reward> getRewards() {
    List<Reward> rewards = new ArrayList<Reward>();
    //  get customerIds
    HashMap<String, String> customerIdMap = new HashMap<>();
    for (Transaction transaction : this.dumpDB) {
      String curId = transaction.getCustomerId();
      String customerId = customerIdMap.get(curId);
      if (customerId == null) {
        customerIdMap.put(curId, curId);
      }
    }
    //  get reward
    for (String customerId : customerIdMap.values()) {
      Reward reward = getRewardByCustomer(customerId);
      rewards.add(reward);
    }
    return rewards;
  }

  private double calculatePoints(double amount) {
    // $120 purchase = 2x$20 + 1x$50 = 90 points
    //  x2 part
    double x2Points = amount > 100 ? (amount - 100) * 2 : 0;
    //  x1 part
    double x1Points = amount >= 100 ? 50 : amount < 100 && amount > 50 ? (amount - 50) : 0;
    return x2Points + x1Points;
  }

  public Reward getRewardByCustomer(String customerId) {
    HashMap<String, Reward> map = new HashMap<>();
    Reward reward = null;
    for (Transaction transaction : this.dumpDB) {
      if (customerId != null && transaction.getCustomerId().equals(customerId)) {
        if (reward == null) {
          reward = new Reward(customerId, 0);
        }
        double points = calculatePoints(transaction.getAmount());
        RewardItem rewardItem = new RewardItem(transaction.getMonth(), points);
        reward.getRewardItems().add(rewardItem);

        double totalPoints = reward.getTotalPoint() + points;
        reward.setTotalPoint(totalPoints);
      }
    }

    return reward;
  }
}
