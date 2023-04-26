package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.model.Reward;
import com.example.model.Transaction;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
class befoRewardTest {
  @Inject
  @Client("/")
  HttpClient client;

  @Test
  @DisplayName("test GET '/transactions' endpoint")
  public void testTransactions(RequestSpecification spec) {
    String url = "/transactions";
    var request = HttpRequest.GET(url);
    var response = client.toBlocking().exchange(request, String.class);

    var body = response.body();
    List<Transaction> transactions = JsonPath.from(body).get();

    assertEquals(HttpStatus.OK, response.status());
    assertEquals(transactions.size(), 8);
  }

  @Test
  @DisplayName("test GET '/rewards/{customerId}' endpoint")
  public void testRewardWithCustomerId(RequestSpecification spec) {
    String url = "/rewards/cust4";
    var request = HttpRequest.GET(url);
    var response = client.toBlocking().exchange(request, String.class);

    var body = response.body();
    var totalPoint = JsonPath.from(body).getDouble("totalPoint");

    assertEquals(HttpStatus.OK, response.status());
    assertEquals(totalPoint, 90);
  }

  @Test
  @DisplayName("test GET '/rewards' endpoint")
  public void testRewards(RequestSpecification spec) {
    String url = "/rewards";
    var request = HttpRequest.GET(url);
    var response = client.toBlocking().exchange(request, String.class);

    var body = response.body();
    List<Reward> rewards = JsonPath.from(body).get();
    assertEquals(HttpStatus.OK, response.status());
    assertEquals(rewards.size(), 4);
  }
}
