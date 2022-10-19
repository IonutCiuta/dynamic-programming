package com.ionutciuta.dynamicprogramming.problem01;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;

import java.util.HashMap;
import java.util.Map;

public class PathsCalculator {
  private static PathsCalculator instance;

  private PathsCalculator() {}

  public static synchronized PathsCalculator getInstance() {
    if (instance == null) {
      instance = new PathsCalculator();
    }
    return instance;
  }

  private int computeRecursively(int x, int y, IntMatrix m) {
    if (x == m.getRowCount() - 1 && y == m.getColumnCount() - 1) {
      return 1;
    }

    if (x < m.getRowCount() && y < m.getColumnCount() && m.getValue(x, y) == 0) {
      return computeRecursively(x + 1, y, m) + computeRecursively(x, y + 1, m);
    }

    return 0;
  }

  public int computeRecursively(IntMatrix m) {
    return computeRecursively(0, 0, m);
  }

  // DP top-down
  public int computeRecursivelyWithMemo(IntMatrix m) {
    return computeRecursivelyWithMemo(0, 0, m, new HashMap<>());
  }

  private int computeRecursivelyWithMemo(int x, int y, IntMatrix m, Map<String, Integer> cache) {
    if (x == m.getRowCount() - 1 && y == m.getColumnCount() - 1) {
      return 1;
    }

    if (x < m.getRowCount() && y < m.getColumnCount() && m.getValue(x, y) == 0) {
      final var key = keyOf(x, y);
      if (cache.containsKey(key)) {
        return cache.get(key);
      }
      final var paths =
          computeRecursivelyWithMemo(x + 1, y, m, cache)
              + computeRecursivelyWithMemo(x, y + 1, m, cache);
      cache.put(key, paths);
      return paths;
    }

    return 0;
  }

  private String keyOf(int x, int y) {
    return x + ":" + y;
  }
}
