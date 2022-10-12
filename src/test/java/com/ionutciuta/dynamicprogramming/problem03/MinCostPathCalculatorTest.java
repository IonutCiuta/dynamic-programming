package com.ionutciuta.dynamicprogramming.problem03;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;
import com.ionutciuta.dynamicprogramming.tools.MatrixGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinCostPathCalculatorTest {

  @Test
  public void shouldReturnMinCostPath() {
    var m =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {1, 2},
              new Integer[] {1, 2}
            });

    assertEquals(4, MinCostPathCalculator.computeRecursively(m));
    assertEquals(4, MinCostPathCalculator.computeRecursivelyWithMemo(m));
  }

  @Test
  public void shouldReturnMinCostPath2() {
    var m =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {1, 2},
              new Integer[] {5, 2}
            });

    assertEquals(5, MinCostPathCalculator.computeRecursively(m));
    assertEquals(5, MinCostPathCalculator.computeRecursivelyWithMemo(m));
  }

  @Test
  public void shouldReturnMinCostPath3() {
    var m =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {3, 2, 12, 15, 10},
              new Integer[] {6, 19, 7, 11, 17},
              new Integer[] {8, 5, 12, 32, 21},
              new Integer[] {3, 20, 2, 9, 7}
            });

    assertEquals(52, MinCostPathCalculator.computeRecursively(m));
    assertEquals(52, MinCostPathCalculator.computeRecursivelyWithMemo(m));
  }

  @Test
  public void timeSolutions() {
    final var m = (IntMatrix) MatrixGenerator.getRandomIntMatrix(17, 10);

    long start = System.currentTimeMillis();
    final var costRecursively = MinCostPathCalculator.computeRecursively(m);
    long duration = System.currentTimeMillis() - start;
    System.out.println("computeRecursively: " + duration + " ms");

    start = System.currentTimeMillis();
    final var costRecursivelyWithMemo = MinCostPathCalculator.computeRecursivelyWithMemo(m);
    duration = System.currentTimeMillis() - start;
    System.out.println("computeRecursivelyWithMemo: " + duration + " ms");

    assertEquals(costRecursively, costRecursivelyWithMemo);
  }
}
