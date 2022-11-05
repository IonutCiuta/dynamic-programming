package com.ionutciuta.dynamicprogramming.problem03;

import com.ionutciuta.dynamicprogramming.problem02.HouseRobber;

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
        return 0;
    }
}
