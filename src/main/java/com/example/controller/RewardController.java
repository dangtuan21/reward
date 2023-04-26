package com.example.controller;

import com.example.model.Reward;
import com.example.model.Transaction;
import com.example.service.RewardService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller()
public class RewardController {
  private static final Logger LOG = LoggerFactory.getLogger(RewardController.class);
  private final RewardService service;

  public RewardController(RewardService service) {
    this.service = service;
  }

  @Get(uri = "/rewards", produces = MediaType.APPLICATION_JSON)
  public List<Reward> getRewards() {
    return service.getRewards();
  }

  @Get(uri = "/rewards/{customerId}", produces = MediaType.APPLICATION_JSON)
  public Reward getRewardByCustomer(String customerId) {
    return service.getRewardByCustomer(customerId);
  }

  @Get(uri = "/transactions", produces = MediaType.APPLICATION_JSON)
  public List<Transaction> getTransactions() {
    return service.getTransactions();
  }
}
