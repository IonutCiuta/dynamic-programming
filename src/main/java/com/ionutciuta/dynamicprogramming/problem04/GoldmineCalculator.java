package com.ionutciuta.dynamicprogramming.problem04;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;

import java.util.HashMap;
import java.util.Map;

public class GoldmineCalculator {
  private static GoldmineCalculator instance;

  private GoldmineCalculator() {}

  public static synchronized GoldmineCalculator getInstance() {
    if (instance == null) {
      instance = new GoldmineCalculator();
    }
    return instance;
  }

  public int computeWithMemo(IntMatrix mine) {
    return computeMaxGold(0, mine, new HashMap<>());
  }

  private int computeMaxGold(int j, IntMatrix mine, Map<String, Integer> cache) {
    if (j >= mine.getColumnCount()) {
      return 0;
    }
    return Math.max(computeMaxGold(0, j, mine, cache), computeMaxGold(j + 1, mine, cache));
  }

  private int computeMaxGold(int i, int j, IntMatrix mine, Map<String, Integer> cache) {
    if (i < 0 || i >= mine.getRowCount() || j < 0 || j >= mine.getColumnCount()) {
      return 0;
    }

    final var key = keyOf(i, j);
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    final var gold =
        mine.getValue(i, j)
            + Math.max(
                computeMaxGold(i + 1, j, mine, cache),
                Math.max(
                    computeMaxGold(i + 1, j + 1, mine, cache),
                    computeMaxGold(i + 1, j - 1, mine, cache)));

    cache.put(key, gold);
    return gold;
  }

  private String keyOf(int i, int j) {
    return i + ":" + j;
  }
}
