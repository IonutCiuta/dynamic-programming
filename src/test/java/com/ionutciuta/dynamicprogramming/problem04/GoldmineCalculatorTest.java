package com.ionutciuta.dynamicprogramming.problem04;

import com.ionutciuta.dynamicprogramming.tools.IntMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoldmineCalculatorTest {
  private static final GoldmineCalculator calculator = GoldmineCalculator.getInstance();

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMaxAmountOfGold_usingMemo(IntMatrix mine, int maxGold) {
    assertEquals(maxGold, calculator.computeWithMemo(mine));
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
            73));
  }
}
