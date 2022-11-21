package com.ionutciuta.dynamicprogramming.problem06;

import java.util.HashMap;
import java.util.Map;

public class NStairsCalculator {
  private static NStairsCalculator instance;

  private NStairsCalculator() {}

  public static synchronized NStairsCalculator getInstance() {
    if (instance == null) {
      instance = new NStairsCalculator();
    }
    return instance;
  }

  public int computeRecursively(int stairs, int[] steps) {
    if (stairs < 0) {
      return 0;
    }

    if (stairs == 0) {
      return 1;
    }

    int sum = 0;
    for (int step : steps) {
      sum += computeRecursively(stairs - step, steps);
    }

    return sum;
  }

  public int computeRecursivelyWithMemo(int stairs, int[] steps) {
    return computeRecursivelyWithMemo(stairs, steps, new HashMap<>());
  }

  private int computeRecursivelyWithMemo(int stairs, int[] steps, Map<Integer, Integer> cache) {
    if (stairs < 0) {
      return 0;
    }

    if (stairs == 0) {
      return 1;
    }

    if (cache.containsKey(stairs)) {
      return cache.get(stairs);
    }

    int sum = 0;
    for (int step : steps) {
      final var remainingStairs = stairs - step;
      sum += computeRecursivelyWithMemo(remainingStairs, steps, cache);
    }

    cache.put(stairs, sum);
    return sum;
  }
}
