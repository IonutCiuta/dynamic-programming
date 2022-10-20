package com.ionutciuta.dynamicprogramming.problem01;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathsCalculatorTest {
  private static final PathsCalculator calculator = PathsCalculator.getInstance();

  @Test
  public void shouldReturnNumberOfPaths() {
    IntMatrix m1 =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {0, 1},
              new Integer[] {0, 0}
            });

    assertEquals(1, calculator.computeRecursively(m1));

    IntMatrix m2 =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {0, 0},
              new Integer[] {1, 0}
            });

    assertEquals(1, calculator.computeRecursively(m2));
  }

  @Test
  public void shouldReturnNumberOfPathsRecursively() {
    int expected = 7;
    IntMatrix m =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {0, 0, 1, 0, 1},
              new Integer[] {0, 0, 0, 0, 1},
              new Integer[] {0, 0, 1, 0, 0},
              new Integer[] {1, 0, 0, 0, 0}
            });

    assertEquals(expected, calculator.computeRecursively(m));
    assertEquals(expected, calculator.computeRecursivelyWithMemo(m));
    assertEquals(expected, calculator.computeRecursively(m));
  }
}
