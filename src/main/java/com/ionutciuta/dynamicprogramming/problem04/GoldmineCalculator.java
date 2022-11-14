package com.ionutciuta.dynamicprogramming.problem04;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;

import java.util.Arrays;
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
    // can be iterative; only difference from the "official" solution ❤️
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

  public int computeWithDp(IntMatrix matrix) {
    int[][] dp = new int[matrix.getRowCount()][matrix.getColumnCount()];

    // copy first row from input matrix
    for (int j = 0; j < matrix.getColumnCount(); j++) {
      dp[0][j] = matrix.getValue(0, j);
    }

    // populate each row with value of matrix(i, j) + max(topLeft, top, topRight)
    for (int i = 1; i < matrix.getRowCount(); i++) {
      for (int j = 0; j < matrix.getColumnCount(); j++) {
        // look for topLeft if possible
        int topLeft = 0;
        if (j > 0) {
          topLeft = dp[i - 1][j - 1];
        }

        // look for topRight if possible
        int topRight = 0;
        if (j < matrix.getColumnCount() - 1) {
          topRight = dp[i - 1][j + 1];
        }

        // look directly above
        int top = dp[i - 1][j];

        // add cell value to max of previous possible origin values
        dp[i][j] = matrix.getValue(i, j) + Math.max(topLeft, Math.max(top, topRight));
      }
    }

    // result is max of last row from dp
    return Arrays.stream(dp[dp.length - 1]).max().orElseThrow();
  }

  private String keyOf(int i, int j) {
    return i + ":" + j;
  }
}
