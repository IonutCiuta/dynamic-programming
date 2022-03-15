package com.ionutciuta.dynamicprogramming.tools;

public class Matrix<T> {
  private final T[][] data;

  public Matrix(T[][] data) {
    this.data = data;
  }

  public int getRowCount() {
    return data.length;
  }

  public int getColumnCount() {
    return data[0].length;
  }

  public T getValue(int row, int column) {
    return data[row][column];
  }
}
