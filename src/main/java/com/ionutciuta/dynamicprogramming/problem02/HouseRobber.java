package com.ionutciuta.dynamicprogramming.problem02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HouseRobber {
  private static HouseRobber instance;

  private HouseRobber() {}

  public static synchronized HouseRobber getInstance() {
    if (instance == null) {
      instance = new HouseRobber();
    }
    return instance;
  }

  public int computeRecursively(int[] houses) {
    if (houses.length == 0) {
      return 0;
    }

    final var results = new ArrayList<Integer>();
    for (int i = 0; i < houses.length; i++) {
      results.add(computeRecursively(houses, i, 0));
    }

    return results.stream().max(Comparator.naturalOrder()).orElse(0);
  }

  private int computeRecursively(int[] houses, int current, int money) {
    if (current >= houses.length) {
      return money;
    }

    final var newMoney = money + houses[current];
    final var results = new ArrayList<Integer>();
    results.add(newMoney);

    for (var i = current + 2; i < houses.length; i++) {
      results.add(computeRecursively(houses, i, newMoney));
    }

    return results.stream().max(Comparator.naturalOrder()).orElse(0);
  }

  // From the course, therefore smarter :)
  public int recursiveSolution(int[] houses) {
    if (houses.length == 0) {
      return 0;
    }
    return recursiveSolution(houses, 0);
  }

  public int recursiveSolution(int[] houses, int current) {
    if (current >= houses.length) {
      return 0;
    }

    return Math.max(
        houses[current]
            + recursiveSolution(
                houses, current + 2), // rob the current house and try the next allowed one
        recursiveSolution(
            houses, current + 1)); // don't rob the current and try the next one directly
  }

  public int recursiveSolutionWithMemo(int[] houses) {
    if (houses.length == 0) {
      return 0;
    }
    return recursiveSolutionWithMemo(houses, 0, new HashMap<Integer, Integer>());
  }

  public int recursiveSolutionWithMemo(int[] houses, int current, Map<Integer, Integer> cache) {
    if (current >= houses.length) {
      return 0;
    }

    if (cache.containsKey(current)) {
      return cache.get(current);
    }

    final var result =
        Math.max(
            houses[current]
                + recursiveSolutionWithMemo(
                    houses,
                    current + 2,
                    cache), // rob the current house and try the next allowed one
            recursiveSolutionWithMemo(
                houses, current + 1, cache)); // don't rob the current and try the next one directly

    cache.put(current, result);
    return result;
  }

  public int dpSolution(int[] houses) {
    if (houses.length == 0) {
      return 0;
    }

    if (houses.length == 1) {
      return houses[0];
    }

    int[] dp = new int[houses.length];
    dp[0] = houses[0]; // we can only steal the amount available at the 1st house
    dp[1] = houses[1]; // we can only steal the amount available at the second house

    for (int i = 2; i < dp.length; i++) {
      // we either steal the amount at the current house and the first non-adjacent previously
      // explored house, or we steal just the amount available at the current house
      dp[i] = Math.max(dp[i - 2] + houses[i], dp[i - 1]);
    }

    return dp[dp.length - 1];
  }

  public int dpSolutionWithSpaceOptimization(int[] houses) {
    if (houses.length == 0) {
      return 0;
    }

    if (houses.length == 1) {
      return houses[0];
    }

    int prevPrev = houses[0];
    int prev = houses[1];
    int current = Math.max(prevPrev, prev);

    for (int i = 2; i < houses.length; i++) {
      current = Math.max(prevPrev + houses[i], prev);
      prevPrev = prev;
      prev = current;
    }

    return current;
  }
}
