package com.ionutciuta.dynamicprogramming.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixGeneratorTest {

  @Test
  public void shouldReturn10x10MatrixWithDefaultMaxValue() {
    var m = MatrixGenerator.getRandomIntMatrix(10);
    assertEquals(10, m.getRowCount());
    assertEquals(10, m.getColumnCount());
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        var v = m.getValue(i, j);
        assertTrue(v >= 0 && v < 10);
      }
    }
  }

  @Test
  public void shouldReturn12x12dMatrixWithDefaultMaxValue100() {
    var m = MatrixGenerator.getRandomIntMatrix(12, 100);
    assertEquals(12, m.getRowCount());
    assertEquals(12, m.getColumnCount());
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 12; j++) {
        var v = m.getValue(i, j);
        assertTrue(v >= 0 && v < 100);
      }
    }
  }

  @Test
  public void shouldReturnArrayInitializedMatrix() {
    var m = new IntMatrix(new Integer[][]{
            new Integer[]{ 1, 2 },
            new Integer[]{ 3, 4 }
    });

    assertEquals(1, m.getValue(0, 0));
    assertEquals(2, m.getValue(0, 1));
    assertEquals(3, m.getValue(1, 0));
    assertEquals(4, m.getValue(1, 1));
  }
}
