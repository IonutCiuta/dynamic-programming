package com.ionutciuta.dynamicprogramming.problem02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {
    @Test
    public void testReturnValue() {
        assertEquals(3628800, FactorialCalculator.computeRecursively(10));
        assertEquals(3628800, FactorialCalculator.computeRecursivelyWithMemo(10));
        assertEquals(3628800, FactorialCalculator.computeIterativelyWithArray(10));
        assertEquals(3628800, FactorialCalculator.computeIteratively(10));
    }
}