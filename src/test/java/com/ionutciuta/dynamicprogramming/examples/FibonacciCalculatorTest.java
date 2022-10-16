package com.ionutciuta.dynamicprogramming.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciCalculatorTest {

  @Test
  public void testReturnValue() {
    assertEquals(55L, FibonacciCalculator.computeRecursively(10L));
    assertEquals(55L, FibonacciCalculator.computeWithMemo(10L));
    assertEquals(55L, FibonacciCalculator.computeIterativelyWithArray(10L));
    assertEquals(55L, FibonacciCalculator.computeIteratively(10L));
  }

  @Test
  public void measureTime() {
    Function<Long, Long> computeRecursively = FibonacciCalculator::computeRecursively;
    Function<Long, Long> computeWithMemo = FibonacciCalculator::computeWithMemo;
    Function<Long, Long> computeIterativelyWithArray = FibonacciCalculator::computeIterativelyWithArray;
    Function<Long, Long> computeIteratively = FibonacciCalculator::computeIteratively;

    List.of(
        // computeRecursively, // won't finish
        computeWithMemo,
        computeIterativelyWithArray,
        computeIteratively
    ).forEach(f -> timeExecution(f, 500L));
  }

  public void timeExecution(Function<Long, Long> function, long input) {
    long start = System.currentTimeMillis();
    var result = function.apply(input);
    long end = System.currentTimeMillis() - start;
    System.out.println("Execution took: " + end + " ms. Result: " + result);
  }
}
