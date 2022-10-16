package com.ionutciuta.dynamicprogramming.problem01;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;

public class PathsCalculator {
  private static PathsCalculator instance;

  private PathsCalculator() {}

  public static synchronized PathsCalculator getInstance() {
    if (instance == null) {
      instance = new PathsCalculator();
    }
    return instance;
  }

  public int computeRecursively(int x, int y, IntMatrix m) {
    if (x == m.getRowCount() - 1 && y == m.getColumnCount() - 1) {
      return 1;
    }

    if (x < m.getRowCount()
            && y < m.getColumnCount()
            && m.getValue(x, y) == 0) {
      return computeRecursively(x + 1, y, m) + computeRecursively(x, y + 1, m);
    }

    return 0;
  }

  public int computeRecursively(IntMatrix m) {
    return computeRecursively(0, 0, m);
  }
}
