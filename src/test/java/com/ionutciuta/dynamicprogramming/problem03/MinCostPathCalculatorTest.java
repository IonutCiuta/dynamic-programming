package com.ionutciuta.dynamicprogramming.problem03;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinCostPathCalculatorTest {

  @Test
  public void shouldReturnMinCostRecursively1() {
    var m =
        new IntMatrix(
            new Integer[][] {
              new Integer[] {1, 2},
              new Integer[] {1, 2}
            });

    assertEquals(4, MinCostPathCalculator.computeRecursively(m));
  }

    @Test
    public void shouldReturnMinCostRecursively2() {
        var m =
                new IntMatrix(
                        new Integer[][] {
                                new Integer[] {1, 2},
                                new Integer[] {5, 2}
                        });

        assertEquals(5, MinCostPathCalculator.computeRecursively(m));
    }

    @Test
    public void shouldReturnMinCostRecursively3() {
        var m =
                new IntMatrix(
                        new Integer[][] {
                                new Integer[] { 3,  2, 12, 15, 10 },
                                new Integer[] { 6, 19,  7, 11, 17 },
                                new Integer[] { 8,  5, 12, 32, 21 },
                                new Integer[] { 3, 20,  2,  9,  7 }
                        });

        assertEquals(52, MinCostPathCalculator.computeRecursively(m));
    }
}
