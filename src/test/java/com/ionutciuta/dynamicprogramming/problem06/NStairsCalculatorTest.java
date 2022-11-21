package com.ionutciuta.dynamicprogramming.problem06;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NStairsCalculatorTest {
  private static final NStairsCalculator calculator = NStairsCalculator.getInstance();

  @ParameterizedTest
  @MethodSource("input")
  public void computeRecursively(int stairs, int[] steps, int expected) {
    assertEquals(expected, calculator.computeRecursively(stairs, steps));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void computeRecursivelyWithMemo(int stairs, int[] steps, int expected) {
    assertEquals(expected, calculator.computeRecursivelyWithMemo(stairs, steps));
  }

  public static Stream<Arguments> input() {
    return Stream.of(
        Arguments.of(10, new int[] {2, 4, 5, 8}, 11),
        Arguments.of(2, new int[] {1, 2}, 2),
        Arguments.of(3, new int[] {1, 2}, 3));
  }
}
