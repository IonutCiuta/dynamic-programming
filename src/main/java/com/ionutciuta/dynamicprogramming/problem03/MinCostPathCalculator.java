package com.ionutciuta.dynamicprogramming.problem03;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;

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
      return  matrix.getValue(i, j) + computeRecursively(i, j + 1, matrix);
    }

    if (j == matrix.getColumnCount() - 1) {
      return matrix.getValue(i, j) + computeRecursively(i + 1, j, matrix);
    }

    return matrix.getValue(i, j) + Math.min(
            computeRecursively(i + 1, j, matrix),
            computeRecursively(i, j + 1, matrix)
    );
  }
}
