package com.ionutciuta.dynamicprogramming.problem02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseRobberTest {
  private final HouseRobber calculator = HouseRobber.getInstance();

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMostMoneyThatCanBeStolen(int[] houses, int expected) {
    assertEquals(expected, calculator.computeRecursively(houses));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMostMoneyThatCanBeStolen_recursiveCourseSolution(int[] houses, int expected) {
    assertEquals(expected, calculator.recursiveSolution(houses));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMostMoneyThatCanBeStolen_recursiveCourseSolutionWithMemo(int[] houses, int expected) {
    assertEquals(expected, calculator.recursiveSolutionWithMemo(houses));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMostMoneyThatCanBeStolen_dpSolution(int[] houses, int expected) {
    assertEquals(expected, calculator.dpSolution(houses));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnMostMoneyThatCanBeStolen_dpSolutionWithSpaceOptimization(int[] houses, int expected) {
    assertEquals(expected, calculator.dpSolutionWithSpaceOptimization(houses));
  }

  public static Stream<Arguments> input() {
    return Stream.of(
        Arguments.of(new int[] {}, 0),
        Arguments.of(new int[] {10}, 10),
        Arguments.of(new int[] {1, 10}, 10),
        Arguments.of(new int[] {1, 10, 3}, 10),
        Arguments.of(new int[] {1, 10, 10}, 11),
        Arguments.of(new int[] {1, 10, 9, 2}, 12),
        Arguments.of(new int[] {2, 10, 3, 6, 8, 1, 7}, 25),
        Arguments.of(new int[] {4, 8, 12, 1, 2, 10, 3, 6, 8}, 34));
  }
}
