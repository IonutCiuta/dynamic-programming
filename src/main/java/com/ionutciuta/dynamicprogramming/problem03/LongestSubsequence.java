package com.ionutciuta.dynamicprogramming.problem03;

import java.util.HashMap;
import java.util.Map;

public class LongestSubsequence {
  private static LongestSubsequence instance;

  private LongestSubsequence() {}

  public static synchronized LongestSubsequence getInstance() {
    if (instance == null) {
      instance = new LongestSubsequence();
    }
    return instance;
  }

  public int computeRecursively(char[] s1, char[] s2) {
    return computeRecursively(0, 0, s1, s2);
  }

  public int computeRecursively(int i, int j, char[] s1, char[] s2) {
    if (i >= s1.length || j >= s2.length) {
      return 0;
    }

    if (s1[i] == s2[j]) {
      return 1 + computeRecursively(i + 1, j + 1, s1, s2);
    } else {
      return Math.max(computeRecursively(i, j + 1, s1, s2), computeRecursively(i + 1, j, s1, s2));
    }
  }

  public int computeRecursivelyWithMemo(char[] s1, char[] s2) {
    return computeRecursivelyWithMemo(0, 0, s1, s2, new HashMap<>());
  }

  private int computeRecursivelyWithMemo(
      int i, int j, char[] s1, char[] s2, Map<String, Integer> cache) {
    if (i >= s1.length || j >= s2.length) {
      return 0;
    }

    final var key = keyOf(i, j);

    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    int result;
    if (s1[i] == s2[j]) {
      result = 1 + computeRecursivelyWithMemo(i + 1, j + 1, s1, s2, cache);
    } else {
      result =
          Math.max(
              computeRecursivelyWithMemo(i, j + 1, s1, s2, cache),
              computeRecursivelyWithMemo(i + 1, j, s1, s2, cache));
    }

    cache.put(key, result);
    return result;
  }

  private String keyOf(int i, int j) {
    return i + ":" + j;
  }
}
