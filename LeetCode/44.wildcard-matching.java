/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 *
 * https://leetcode.com/problems/wildcard-matching/description/
 *
 * algorithms
 * Hard (22.33%)
 * Total Accepted:    161.7K
 * Total Submissions: 723.8K
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern
 * matching with support for '?' and '*'.
 *
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 *
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like ? or *.
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
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 *
 * Example 3:
 *
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not
 * match 'b'.
 *
 *
 * Example 4:
 *
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*'
 * matches the substring "dce".
 *
 *
 * Example 5:
 *
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 *
 *
 */
/**
 * 给定一个匹配串和一个模式串，模式串可能包含符号 * 和符号 ?
 * ? 匹配任何单一字符
 * * 匹配任何字符串（包括空字符串）
 *
 * Reference https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
 * 使用迭代的方式处理，详细请看注释
 * 另外也可以用类似第十题的 DP，速度比这个稍慢
 */
class Solution {
    public boolean isMatch(String s, String p) {
        // 指向当前 s 和 p 正在比较的位置
        int sIndex = 0, pIndex = 0;
        // * 号出现或匹配后，记录 * 号和主字符串匹配当前的位置
        int sIndex4LastStar = -1;
        // * 号出现后，继续其位置
        int lastStar = -1;
        while (sIndex < s.length()) {
            if (pIndex < p.length() && (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '?')) {
                // 字符串正常匹配（包括 ? 号匹配）
                sIndex++;
                pIndex++;
            } else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
                // 字符串不匹配，遇到 * 号
                // 惰性：我们假设这个 * 号什么都不匹配

                // 记录下这个星号的位置
                lastStar = pIndex;
                // 记录下现在 * 号和主字符串匹配的位置，（ 0 个匹配也算是匹配的一种）
                sIndex4LastStar = sIndex;
                // 从下一个符号开始匹配
                pIndex++;
            } else if (sIndex4LastStar != -1) {
                // 字符串不匹配，但是之前有星号可用

                // 惰性：使用 * 匹配一位，并将 * 号和主字符串匹配的位置 + 1
                sIndex = ++sIndex4LastStar;
                // 由于上述惰性，假设 * 号的匹配到此为止了，从 * 号下一位开始匹配
                pIndex = lastStar + 1;
            } else {
                // 没有任何匹配，失败
                return false;
            }
        }
        // 如果还有 * 号，那么直接设这些 * 号不匹配任何东西，跳过即可
        while (pIndex < p.length() && p.charAt(pIndex) == '*')
            pIndex++;
        return pIndex == p.length();
    }
}
