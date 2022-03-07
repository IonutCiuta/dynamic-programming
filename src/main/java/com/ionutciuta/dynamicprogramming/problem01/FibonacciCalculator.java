package com.ionutciuta.dynamicprogramming.problem01;

import java.util.HashMap;

public class FibonacciCalculator {
  private FibonacciCalculator() {}

  public static int computeRecursively(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    else return computeRecursively(n - 1) + computeRecursively(n - 2);
  }

  public static int computeWithMemo(int n) {
    return computeWithMemo(n, new HashMap<>());
  }

  private static int computeWithMemo(int n, HashMap<Integer, Integer> cache) {
    if (cache.containsKey(n)) return cache.get(n);
    if (n == 0) return 0;
    if (n == 1) return 1;
    var r = computeWithMemo(n - 1, cache) + computeWithMemo(n - 2, cache);
    cache.put(n, r);
    return r;
  }

  public static int computeIterativelyWithArray(int n) {
    var arr = new Integer[n + 1];
    arr[0] = 0;
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[n];
  }

  public static int computeIteratively(int n) {
    var prevOfPrev = 0;
    var prev = 1;
    var curr = 0;
    for (int i = 2; i <= n; i++) {
      curr = prevOfPrev + prev;
      prevOfPrev = prev;
      prev = curr;
    }
    return curr;
  }
}
