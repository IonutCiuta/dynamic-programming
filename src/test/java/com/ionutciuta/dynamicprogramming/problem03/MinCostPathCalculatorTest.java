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
    assertEquals(4, MinCostPathCalculator.computeIteratively(m));
    assertEquals(4, MinCostPathCalculator.computeIterativelyOptimized(m));
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
    assertEquals(5, MinCostPathCalculator.computeIteratively(m));
    assertEquals(5, MinCostPathCalculator.computeIterativelyOptimized(m));
  }

  @Test
  public void shouldReturnMinCostPath3() {
    final var m =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {3, 2, 12, 15, 10},
              new Integer[] {6, 19, 7, 11, 17},
              new Integer[] {8, 5, 12, 32, 21},
              new Integer[] {3, 20, 2, 9, 7}
            });

    assertEquals(52, MinCostPathCalculator.computeRecursively(m));
    assertEquals(52, MinCostPathCalculator.computeRecursivelyWithMemo(m));
    assertEquals(52, MinCostPathCalculator.computeIteratively(m));
    assertEquals(52, MinCostPathCalculator.computeIterativelyOptimized(m));
  }

  @Test
  public void timeAllSolutions() {
    final var m = (IntMatrix) MatrixGenerator.getRandomIntMatrix(17, 10);

    long start = System.currentTimeMillis();
    final var baseline = MinCostPathCalculator.computeRecursively(m);
    long duration = System.currentTimeMillis() - start;
    System.out.println("computeRecursively: " + duration + " ms");

    start = System.currentTimeMillis();
    final var costRecursivelyWithMemo = MinCostPathCalculator.computeRecursivelyWithMemo(m);
    duration = System.currentTimeMillis() - start;
    System.out.println("computeRecursivelyWithMemo: " + duration + " ms");

    start = System.currentTimeMillis();
    final var costIteratively = MinCostPathCalculator.computeIteratively(m);
    duration = System.currentTimeMillis() - start;
    System.out.println("computeIteratively: " + duration + " ms");

    start = System.currentTimeMillis();
    final var costIterativelyOptimized = MinCostPathCalculator.computeIterativelyOptimized(m);
    duration = System.currentTimeMillis() - start;
    System.out.println("computeIterativelyOptimized: " + duration + " ms");

    assertEquals(baseline, costRecursivelyWithMemo);
    assertEquals(baseline, costIteratively);
    assertEquals(baseline, costIterativelyOptimized);
  }

  @Test
  public void timeDpSolutions() {
    final var m = (IntMatrix) MatrixGenerator.getRandomIntMatrix(500, 10);

    long start = System.currentTimeMillis();
    final var baseline = MinCostPathCalculator.computeRecursivelyWithMemo(m);
    long duration = System.currentTimeMillis() - start;
    System.out.println("computeRecursivelyWithMemo: " + duration + " ms");

    start = System.currentTimeMillis();
    final var costIteratively = MinCostPathCalculator.computeIteratively(m);
    duration = System.currentTimeMillis() - start;
    System.out.println("computeIteratively: " + duration + " ms");

    start = System.currentTimeMillis();
    final var costIterativelyOptimized = MinCostPathCalculator.computeIterativelyOptimized(m);
    duration = System.currentTimeMillis() - start;
    System.out.println("computeIterativelyOptimized: " + duration + " ms");

    assertEquals(baseline, costIteratively);
    assertEquals(baseline, costIterativelyOptimized);
  }

  @Test
  public void timeDpIterativeSolutions() {
    final var m = (IntMatrix) MatrixGenerator.getRandomIntMatrix(10000, 10);

    var start = System.currentTimeMillis();
    final var baseline = MinCostPathCalculator.computeIteratively(m);
    var duration = System.currentTimeMillis() - start;
    System.out.println("computeIteratively: " + duration + " ms");

    start = System.currentTimeMillis();
    final var costIterativelyOptimized = MinCostPathCalculator.computeIterativelyOptimized(m);
    duration = System.currentTimeMillis() - start;
    System.out.println("computeIterativelyOptimized: " + duration + " ms");

    assertEquals(baseline, costIterativelyOptimized);
  }
}
