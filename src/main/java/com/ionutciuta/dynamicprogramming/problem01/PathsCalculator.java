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

  public int computeRecursively(IntMatrix m) {
    return computeRecursively(0, 0, m);
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

  private int computeIteratively(IntMatrix matrix) {
    final var m = matrix.getRowCount();
    final var n = matrix.getColumnCount();
    final var dp = new int[m][n];

    // explore first row to the right
    for (var i = 1; i < n; i++) {
      if (matrix.getValue(0, i) == 0) {
        dp[0][i] = 1; // there is only one way to get here - going right
      } else {
        break; // no point in looking forward if we can't move further to the right
      }
    }

    // explore first column down
    for (var i = 1; i < m; i++) {
      if (matrix.getValue(i, 0) == 0) {
        dp[i][0] = 1; // there is only one way to get here - going down
      } else {
        break; // no point in looking forward if we can't move further down
      }
    }

    for (var i = 1; i < m; i++) {
      for (var j = 1; j < n; j++) {
        if (matrix.getValue(i, j) == 0) { // if there's no wall
          dp[i][j] = dp[i][j - 1] + dp[i - 1][j]; // look back and above
        } else {
          dp[i][j] = 0; // there's no way to get here
        }
      }
    }

    // bottom right corner contains the answer
    return dp[m - 1][n - 1];
  }
}
