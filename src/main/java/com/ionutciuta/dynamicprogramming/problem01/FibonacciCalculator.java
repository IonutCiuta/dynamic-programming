package com.ionutciuta.dynamicprogramming.problem01;

import java.util.HashMap;

public class FibonacciCalculator {
  private FibonacciCalculator() {}

  public static long computeRecursively(long n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    else return computeRecursively(n - 1) + computeRecursively(n - 2);
  }

  public static long computeWithMemo(long n) {
    return computeWithMemo(n, new HashMap<>());
  }

  private static long computeWithMemo(long n, HashMap<Long, Long> cache) {
    if (cache.containsKey(n)) return cache.get(n);
    if (n == 0) return 0L;
    if (n == 1) return 1L;
    var r = computeWithMemo(n - 1, cache) + computeWithMemo(n - 2, cache);
    cache.put(n, r);
    return r;
  }

  public static long computeIterativelyWithArray(long n) {
    var arr = new Long[(int) n + 1];
    arr[0] = 0L;
    arr[1] = 1L;
    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[(int) n];
  }

  public static long computeIteratively(long n) {
    var prevOfPrev = 0L;
    var prev = 1L;
    var curr = 0L;
    for (long i = 2; i <= n; i++) {
      curr = prevOfPrev + prev;
      prevOfPrev = prev;
      prev = curr;
    }
    return curr;
  }
}
