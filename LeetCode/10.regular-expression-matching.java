/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (24.94%)
 * Total Accepted:    274.9K
 * Total Submissions: 1.1M
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*'.
 * 
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * 
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like . or *.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore
 * it matches "aab".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 * 
 * 
 */
/**
 * Use Approach 2 in Solution: Dynamic Programming
 * 最简单的做法是直接使用递归
 * 但是递归会造成大量的重复计算
 * 因此使用一个数组将之前计算的结果缓存起来
 */
class Solution {
    // dp[i][j] means: is text[i...] matches pattern[j...]
    Boolean[][] dp;

    public boolean isMatch(String s, String p) {
        // 多申请一位是为了防止越界
        // 也可以为了节约内存，申请恰好的长度，但是要做更多的边界情况检查
        dp = new Boolean[s.length() + 1][p.length() + 1];
        return isMatch(0, 0, s, p);
    }

    private boolean isMatch(int sIndex, int pIndex, String s, String p) {
        if (dp[sIndex][pIndex] != null)
            return dp[sIndex][pIndex];
        boolean answer;
        if (pIndex == p.length()) {
            answer = sIndex == s.length();
        } else {
            // 计算第一个字符是否匹配
            boolean firstMatch = (
            // 如果源字符串已经空了，自然不匹配
            sIndex < s.length() &&
            // 相等和 . 符号都是匹配
                    (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.'));
            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                // 如果下一位是 * ，那么要么不匹配任何字符，要么继续用 * 号匹配下去
                answer = isMatch(sIndex, pIndex + 2, s, p) || (firstMatch && isMatch(sIndex + 1, pIndex, s, p));
            } else {
                // 如果不是 * ，继续匹配
                answer = firstMatch && isMatch(sIndex + 1, pIndex + 1, s, p);
            }
        }
        // 缓存当前结果
        dp[sIndex][pIndex] = answer;
        return answer;
    }
}
