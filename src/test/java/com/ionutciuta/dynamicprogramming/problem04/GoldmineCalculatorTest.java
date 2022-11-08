package com.ionutciuta.dynamicprogramming.problem04;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;
import com.ionutciuta.dynamicprogramming.tools.MatrixGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GoldmineCalculatorTest {
  private static final GoldmineCalculator calculator = GoldmineCalculator.getInstance();

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMaxAmountOfGold_usingMemo(IntMatrix mine, int maxGold) {
    assertEquals(maxGold, calculator.computeWithMemo(mine));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMaxAmountOfGold_usingDp(IntMatrix mine, int maxGold) {
    assertEquals(maxGold, calculator.computeWithDp(mine));
  }

  @Test
  public void shouldReturnSameResultForBigMatrix() {
    IntMatrix m = (IntMatrix) MatrixGenerator.getRandomIntMatrix(1500, 10);

    long memoStart = System.currentTimeMillis();
    int memoResult = calculator.computeWithMemo(m);
    long memoTime = System.currentTimeMillis() - memoStart;

    long dpStart = System.currentTimeMillis();
    int dpResult = calculator.computeWithDp(m);
    long dpTime = System.currentTimeMillis() - dpStart;

    assertEquals(memoResult, dpResult);
    System.out.println("Memo took: " + memoTime + "ms");
    System.out.println("DP   took: " + dpTime + "ms");
    assertTrue(memoTime > dpTime);
  }

  public static Stream<Arguments> input() {
    return Stream.of(
        Arguments.of(
            new IntMatrix(
                new Integer[][] {
                  {4, 5, 4},
                  {7, 4, 8},
                  {2, 9, 1},
                  {0, 1, 0}
                }),
            23),
        Arguments.of(
            new IntMatrix(
                new Integer[][] {
                  {3, 2, 12, 15, 10},
                  {6, 19, 7, 11, 17},
                  {8, 5, 12, 32, 21},
                  {3, 20, 2, 9, 7}
                }),
            73),
        Arguments.of(
            new IntMatrix(
                new Integer[][] {
                  {2, 3, 4, 2},
                  {9, 7, 4, 3},
                  {1, 2, 1, 10}
                }),
            18));
  }
}
