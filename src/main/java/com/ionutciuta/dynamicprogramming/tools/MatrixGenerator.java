package com.ionutciuta.dynamicprogramming.tools;

import java.util.Random;

public class MatrixGenerator {
  private static final int MAX_VALUE = 10;
  private static final Random random = new Random();

  private MatrixGenerator() {}

  public static Matrix<Integer> getRandomIntMatrix(int size) {
    return getRandomIntMatrix(size, MAX_VALUE);
  }

  public static Matrix<Integer> getRandomIntMatrix(int size, int maxCellValue) {
    var data = new Integer[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        data[i][j] = random.nextInt(maxCellValue);
      }
    }
    return new Matrix<>(data);
  }
}
