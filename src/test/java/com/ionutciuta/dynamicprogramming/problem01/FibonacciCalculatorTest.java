package com.ionutciuta.dynamicprogramming.problem01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciCalculatorTest {

    @Test
    public void test() {
        assertEquals(55, FibonacciCalculator.computeRecursively(10));
        assertEquals(55, FibonacciCalculator.computeWithMemo(10));
        assertEquals(55, FibonacciCalculator.computeIterativelyWithArray(10));
        assertEquals(55, FibonacciCalculator.computeIteratively(10));
    }
}