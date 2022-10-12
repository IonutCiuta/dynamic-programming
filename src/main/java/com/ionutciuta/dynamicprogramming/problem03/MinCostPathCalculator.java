package com.ionutciuta.dynamicprogramming.problem03;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;

import java.util.HashMap;
import java.util.Map;

public class MinCostPathCalculator {
  private MinCostPathCalculator() {}

  public static int computeRecursively(IntMatrix matrix) {
    return computeRecursively(0, 0, matrix);
  }

  private static int computeRecursively(int i, int j, IntMatrix matrix) {
    if (i == matrix.getRowCount() - 1 && j == matrix.getColumnCount() - 1) {
      return matrix.getValue(i, j);
    }

    if (i == matrix.getRowCount() - 1) {
      return matrix.getValue(i, j) + computeRecursively(i, j + 1, matrix);
    }

    if (j == matrix.getColumnCount() - 1) {
      return matrix.getValue(i, j) + computeRecursively(i + 1, j, matrix);
    }

    return matrix.getValue(i, j)
        + Math.min(computeRecursively(i + 1, j, matrix), computeRecursively(i, j + 1, matrix));
  }

  // DP top-down
  public static int computeRecursivelyWithMemo(IntMatrix matrix) {
    final var cache = new HashMap<String, Integer>();
    return computeRecursivelyWithMemo(0, 0, matrix, cache);
  }

  private static int computeRecursivelyWithMemo(
      int i, int j, IntMatrix matrix, Map<String, Integer> cache) {
    final var key = keyOf(i, j);
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    // base case
    if (i == matrix.getRowCount() - 1 && j == matrix.getColumnCount() - 1) {
      return matrix.getValue(i, j);
    }

    if (i == matrix.getRowCount() - 1) {
      final var cost = matrix.getValue(i, j) + computeRecursivelyWithMemo(i, j + 1, matrix, cache);
      cache.put(key, cost);
      return cost;
    }

    if (j == matrix.getColumnCount() - 1) {
      final var cost = matrix.getValue(i, j) + computeRecursivelyWithMemo(i + 1, j, matrix, cache);
      cache.put(key, cost);
      return cost;
    }

    // target is value at (0, 0)
    final var cost =
        matrix.getValue(i, j)
            + Math.min(
                computeRecursivelyWithMemo(i + 1, j, matrix, cache),
                computeRecursivelyWithMemo(i, j + 1, matrix, cache));
    cache.put(key, cost);
    return cost;
  }

  private static String keyOf(int x, int y) {
    return x + ":" + y;
  }

  // DP bottom-up
  public static int computeIteratively(IntMatrix matrix) {
    final var m = matrix.getRowCount();
    final var n = matrix.getColumnCount();
    final var dp = new int[m][n];

    // base case
    dp[0][0] = matrix.getValue(0, 0);

    for (int j = 1; j < n; j++) {
      dp[0][j] += dp[0][j - 1] + matrix.getValue(0, j);
    }

    for (int i = 1; i < n; i++) {
      dp[i][0] += dp[i - 1][0] + matrix.getValue(i, 0);
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] =
            matrix.getValue(i, j)
                + Math.min(
                    dp[i - 1][j], // value above
                    dp[i][j - 1] // value behind
                    );
      }
    }

    // target
    return dp[m - 1][n - 1];
  }
}
