package com.ionutciuta.dynamicprogramming.problem02;

import java.util.HashMap;
import java.util.Map;

public class FactorialCalculator {
  private FactorialCalculator() {}

  public static long computeRecursively(long n) {
    if (n == 1) return 1;
    else return n * computeRecursively(n - 1);
  }

  public static long computeRecursivelyWithMemo(long n) {
    return computeRecursivelyWithMemo(n, new HashMap<>());
  }

  private static long computeRecursivelyWithMemo(long n, Map<Long, Long> cache) {
    if (n == 1) return 1;
    else if (cache.containsKey(n)) return cache.get(n);
    var r = n * computeRecursivelyWithMemo(n - 1, cache);
    cache.put(n, r);
    return r;
  }

  public static long computeIterativelyWithArray(long n) {
    var arr = new Long[(int) n];
    arr[0] = 1L;
    for (int i = 1; i < n; i++) {
      arr[i] = arr[i - 1] * (i + 1);
    }
    return arr[(int) (n - 1)];
  }

  public static long computeIteratively(long n) {
    var acc = 1;
    for (int i = 2; i <= n; i++) {
      acc = acc * i;
    }
    return acc;
  }
}
