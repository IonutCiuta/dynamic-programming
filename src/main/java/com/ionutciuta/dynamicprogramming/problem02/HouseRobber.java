package com.ionutciuta.dynamicprogramming.problem02;

import java.util.ArrayList;
import java.util.Comparator;

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
}
