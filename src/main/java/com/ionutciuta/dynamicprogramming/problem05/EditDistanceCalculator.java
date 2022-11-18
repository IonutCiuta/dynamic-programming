package com.ionutciuta.dynamicprogramming.problem05;

import java.util.HashMap;
import java.util.Map;

public class EditDistanceCalculator {
  private static EditDistanceCalculator instance;

  private EditDistanceCalculator() {}

  public static synchronized EditDistanceCalculator getInstance() {
    if (instance == null) {
      instance = new EditDistanceCalculator();
    }
    return instance;
  }

  public int computeRecursively(String s1, String s2) {
    return computeRecursively(0, 0, s1, s2);
  }

  private int computeRecursively(int i, int j, String s1, String s2) {
    // Base case - we reached the end of both string
    if (i >= s1.length() && j >= s2.length()) {
      return 0;
    }

    // We reached the end of s1, but not of s2; we need to add chars until they are equal in length
    if (i >= s1.length()) {
      return 1 + computeRecursively(i, j + 1, s1, s2);
    }

    // We reached the end of s2, but not of s1; we need to subtract chars until they are equal in
    // length
    if (j >= s2.length()) {
      return 1 + computeRecursively(i + 1, j, s1, s2);
    }

    // if chars are equal, we just move forward
    if (s1.charAt(i) == s2.charAt(j)) {
      return computeRecursively(i + 1, j + 1, s1, s2);
    }

    // chars are not equal, so we definitely need to do one change
    return 1
        + Math.min(
            // We either do a sub and move both pointers, or -
            computeRecursively(i + 1, j + 1, s1, s2),
            Math.min(
                // We do a subtraction, basically skipping to the next char from s1, or -
                computeRecursively(i + 1, j, s1, s2),
                // We do an addition, basically skipping to the next char from s2
                computeRecursively(i, j + 1, s1, s2)));
  }

  public int computeRecursivelyWithMemo(String s1, String s2) {
    return computeRecursivelyWithMemo(0, 0, s1, s2, new HashMap<>());
  }

  private int computeRecursivelyWithMemo(
      int i, int j, String s1, String s2, Map<String, Integer> cache) {

    // check cache
    final var key = keyOf(i, j);
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    // Base case - we reached the end of both string
    if (i >= s1.length() && j >= s2.length()) {
      return 0;
    }

    // prepare for caching
    int result;

    // We reached the end of s1, but not of s2; we need to add chars until they are equal in length
    if (i >= s1.length()) {
      result = 1 + computeRecursivelyWithMemo(i, j + 1, s1, s2, cache);
      cache.put(key, result);
      return result;
    }

    // We reached the end of s2, but not of s1; we need to subtract chars until they are equal in
    // length
    if (j >= s2.length()) {
      result = 1 + computeRecursivelyWithMemo(i + 1, j, s1, s2, cache);
      cache.put(key, result);
      return result;
    }

    // if chars are equal, we just move forward
    if (s1.charAt(i) == s2.charAt(j)) {
      result = computeRecursivelyWithMemo(i + 1, j + 1, s1, s2, cache);
      cache.put(key, result);
      return result;
    }

    // chars are not equal, so we definitely need to do one change
    result =
        1
            + Math.min(
                // We either do a sub and move both pointers, or -
                computeRecursivelyWithMemo(i + 1, j + 1, s1, s2, cache),
                Math.min(
                    // We do a removal, basically skipping to the next char from s1, or -
                    computeRecursivelyWithMemo(i + 1, j, s1, s2, cache),
                    // We do an addition, basically skipping to the next char from s2
                    computeRecursivelyWithMemo(i, j + 1, s1, s2, cache)));
    cache.put(key, result);
    return result;
  }

  public int computeWithOptimizedRecursiveSolution(String s1, String s2) {
    return computeWithOptimizedRecursiveSolution(0, 0, s1, s2, new HashMap<>());
  }

  private int computeWithOptimizedRecursiveSolution(
      int i, int j, String s1, String s2, Map<String, Integer> cache) {

    // check cache
    final var key = keyOf(i, j);
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    // Base cases
    // we reached the end of s1, if s2 is longer we have to add the remaining chars
    if (i == s1.length()) {
      return s2.length() - j;
    }
    // we reached the end of s2, if s1 is longer we have to remove the extra chars
    if (j == s2.length()) {
      return s1.length() - i;
    }

    int result;

    // if chars are equal, we just move forward
    if (s1.charAt(i) == s2.charAt(j)) {
      result = computeWithOptimizedRecursiveSolution(i + 1, j + 1, s1, s2, cache);
      cache.put(key, result);
      return result;
    }

    // chars are not equal, so we definitely need to do one change
    result =
        1
            + Math.min(
                // We either do a sub and move both pointers, or -
                computeWithOptimizedRecursiveSolution(i + 1, j + 1, s1, s2, cache),
                Math.min(
                    // We do a removal, basically skipping to the next char from s1, or -
                    computeWithOptimizedRecursiveSolution(i + 1, j, s1, s2, cache),
                    // We do an addition, basically skipping to the next char from s2
                    computeWithOptimizedRecursiveSolution(i, j + 1, s1, s2, cache)));
    cache.put(key, result);
    return result;
  }

  private String keyOf(int i, int j) {
    return i + ":" + j;
  }

  public int computeWithDp(String s1, String s2) {
    // number of cols for dp with padding of 1
    final int n = s1.length() + 1;
    // number of row for dp with padding of 1
    final int m = s2.length() + 1;

    // padding accounts for empty string
    final var dp = new int[m][n];

    // dp[0][0] is 0 - distance between "" & "" is always 0

    for (int i = 1; i < n; i++) {
      // distance between "" and whatever else is the len of s1, i.e. i
      dp[0][i] = i;
    }

    for (int j = 1; j < m; j++) {
      // similar to above, the distance between "" and s2
      dp[j][0] = j;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (s1.charAt(j - 1) == s2.charAt(i - 1)) {
          // if the current chars are equal, just return the previous distance
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          // as per the recursive function, choose the min between deletion, replacement and
          // addition and add 1
          dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
        }
      }
    }

    // the distance between s1 and s2
    return dp[m - 1][n - 1];
  }
}
