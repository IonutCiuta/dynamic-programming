package com.ionutciuta.dynamicprogramming.problem03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubsequenceTest {
  private final LongestSubsequence calculator = LongestSubsequence.getInstance();

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnLcs_recursive(String s1, String s2, int expected) {
    assertEquals(expected, calculator.computeRecursively(s1.toCharArray(), s2.toCharArray()));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnLcs_recursiveWithMemo(String s1, String s2, int expected) {
    assertEquals(
        expected, calculator.computeRecursivelyWithMemo(s1.toCharArray(), s2.toCharArray()));
  }

  @ParameterizedTest
  @MethodSource("input")
  public void shouldReturnLcs_withDp(String s1, String s2, int expected) {
    assertEquals(
            expected, calculator.computeWithDp(s1.toCharArray(), s2.toCharArray()));
  }

  public static Stream<Arguments> input() {
    return Stream.of(
        Arguments.of("a", "b", 0),
        Arguments.of("a", "a", 1),
        Arguments.of("a", "ba", 1),
        Arguments.of("a", "ab", 1),
        Arguments.of("ab", "ab", 2),
        Arguments.of("ab", "acb", 2),
        Arguments.of("acb", "defgacb", 3),
        Arguments.of("a0c0b", "adefg1c2b3", 3),
        Arguments.of("axby", "1byx", 2));
  }
}
