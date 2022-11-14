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
    }

    // chars are not equal, so we definitely need to do one change
    result =
        1
            + Math.min(
                // We either do a sub and move both pointers, or -
                computeRecursivelyWithMemo(i + 1, j + 1, s1, s2, cache),
                Math.min(
                    // We do a subtraction, basically skipping to the next char from s1, or -
                    computeRecursivelyWithMemo(i + 1, j, s1, s2, cache),
                    // We do an addition, basically skipping to the next char from s2
                    computeRecursivelyWithMemo(i, j + 1, s1, s2, cache)));
    cache.put(key, result);
    return result;
  }

  private String keyOf(int i, int j) {
    return i + ":" + j;
  }
}
