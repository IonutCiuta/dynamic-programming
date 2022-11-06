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

  // From the course :)
  public int computeWithDp(char[] s1, char[] s2) {
    if (s1.length == 0 || s2.length == 0) {
      return 0;
    }

    // Say we have s1 = "abcd" and s2 = "cdef"
    // We need an extra row and column as column because with the DP approach we:
    // try to compute the LCS between various substrings of s1 & s2, including the empty string =>
    // that's why the extra row & column filled with 0
    // e.g. 0) at dp[0, 3] we compute the LCS("", "cde") => always 0
    // e.g. 1) at dp[1, 1] we compute the LCS of s1[0] & s2[0] => LCS("a", "c") => 0
    final var dp = new int[s1.length + 1][s2.length + 1];

    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        // we need to decrement the counters due to the padding
        if (s1[i - 1] == s2[j - 1]) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[s1.length][s2.length];
  }
}
