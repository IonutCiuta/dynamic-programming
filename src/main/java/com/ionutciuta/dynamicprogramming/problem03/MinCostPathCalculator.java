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

  private static int computeRecursivelyWithMemo(int i, int j, IntMatrix matrix, Map<String, Integer> cache) {
    final var key = keyOf(i, j);
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

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
}
