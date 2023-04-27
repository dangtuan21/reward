package com.example;

import com.example.model.Reward;
import com.example.model.Transaction;
import com.example.service.RewardService;
import io.micronaut.context.env.Environment;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(environments = Environment.TEST)
public class RewardServiceTest {
    @Inject
    RewardService rewardService;
    @Test
    @DisplayName("test getRewardByCustomer")
    public void test_getRewardByCustomer(RequestSpecification spec) {
        Reward reward = rewardService.getRewardByCustomer("cust4");
        assertEquals(reward.getTotalPoint(), 90);
    }
    @Test
    @DisplayName("test getRewards")
    public void test_getRewards(RequestSpecification spec) {
        List<Reward> rewards = rewardService.getRewards();
        assertEquals(rewards.size(), 4);
    }
    @Test
    @DisplayName("test getTransactions")
    public void test_getTransactions(RequestSpecification spec) {
        List<Transaction> transactions = rewardService.getTransactions();
        assertEquals(transactions.size(), 8);
    }
    @Test
    @DisplayName("test calculatePoints")
    public void test_calculatePoints(RequestSpecification spec) {
        try {
            Object resultPoints;
            //  test amount =50: resultPoints = 0
            resultPoints = getMethodCalculatePoints().invoke(rewardService, 50);
            assertEquals(resultPoints, 0.0);

            //  test amount =60: resultPoints = 10
            resultPoints = getMethodCalculatePoints().invoke(rewardService, 60);
            assertEquals(resultPoints, 10.0);

            //  test amount =100: resultPoints = 50
            resultPoints = getMethodCalculatePoints().invoke(rewardService, 100);
            assertEquals(resultPoints, 50.0);

            //  test amount =120: resultPoints = 90
            resultPoints = getMethodCalculatePoints().invoke(rewardService, 120);
            assertEquals(resultPoints, 90.0);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private Method getMethodCalculatePoints() throws NoSuchMethodException {
        Method method = RewardService.class.getDeclaredMethod("calculatePoints", double.class);
        method.setAccessible(true);
        return method;
    }
}
