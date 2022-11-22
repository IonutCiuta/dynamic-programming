package com.ionutciuta.dynamicprogramming.problem05;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditDistanceCalculatorTest {
  private static final EditDistanceCalculator calculator = EditDistanceCalculator.getInstance();

  @ParameterizedTest
  @MethodSource("input")
  public void shouldComputeEditDistance_recursively(String s1, String s2, int editDistance) {
    assertEquals(editDistance, calculator.computeRecursively(s1, s2));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldComputeEditDistance_recursivelyWithMemo(
      String s1, String s2, int editDistance) {
    assertEquals(editDistance, calculator.computeRecursivelyWithMemo(s1, s2));
  }


  @ParameterizedTest
  @MethodSource("input")
  public void shouldComputeEditDistance_optimizedRecursiveSolution(
          String s1, String s2, int editDistance) {
    assertEquals(editDistance, calculator.computeWithOptimizedRecursiveSolution(s1, s2));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldComputeEditDistance_withDp(String s1, String s2, int editDistance) {
    assertEquals(editDistance, calculator.computeWithDp(s1, s2));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldComputeEditDistance_withDpOptimized(String s1, String s2, int editDistance) {
    assertEquals(editDistance, calculator.computeWithOptimizedDp(s1, s2));
  }

  public static Stream<Arguments> input() {
    return Stream.of(
        Arguments.of("", "", 0),
        Arguments.of("a", "a", 0),
        Arguments.of("a", "b", 1),
        Arguments.of("a", "ab", 1),
        Arguments.of("ab", "a", 1),
        Arguments.of("ab", "b", 1),
        Arguments.of("abc", "", 3),
        Arguments.of("abc", "abb", 1),
        Arguments.of("abc", "abbdd", 3),
        Arguments.of("abac", "abbxy", 3),
        Arguments.of("inside", "index", 3));
  }
}
