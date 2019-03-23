/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 *
 * https://leetcode.com/problems/shortest-palindrome/description/
 *
 * algorithms
 * Hard (26.94%)
 * Total Accepted:    69.9K
 * Total Submissions: 258.5K
 * Testcase Example:  '"aacecaaa"'
 *
 * Given a string s, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * Example 1:
 * 
 * 
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "abcd"
 * Output: "dcbabcd"
 */
/**
 * Use approach 3 in solution: KMP
 * 首先确定的是，由于只能在字符串前端添加字符，所以为了凑出最短回文
 * 我们需要从输入的字符串中，找到一个最长的回文前缀，然后剩下的部分反转之后加到前面
 * 例如：
 * abacd，最长回文前缀是 aba，剩余 cd，因此将 cd 反转后加到前面即可：dcabacd
 * abcd，最长回文前缀是 a，剩余 bcd，因此将 bcd 反转后加到前面即可：dcbabcd
 * 
 * 因此问题转为如何寻找最长的回文前缀
 * 
 * 最 Naive 的做法：暴力搜索
 * 既然只需要找到最长回文前缀，那么暴力搜索最先被想到
 * 将输入字符串 str 反转保存为 rev，
 * 然后将 str 和 rev 错位对比，直到找到一个回文串
 * 
 * 过程类似如下：
 * 如果两个字符串 str 和 rev 直接相等，那么整个串都是回文的
 *          abad
 *          baba
 *              - 不匹配
 * 
 * 如果 str 的前缀和 rev 的后缀相等，那么这个串就是 str 最长回文前缀
 *          aba(d)
 *       (d)aba
 *              - 匹配
 * 
 * 仔细观察这个过程，我们实际上是在用 str 的所有前缀和 rev 的所有后缀进行对比，找到最长的匹配串
 * 
 * 这不就是 KMP 中的一步吗？
 * 在 KMP 中我们需要生成一个查找数组，table
 * 其中 table[i] 表示 str[0:i]这个串的前缀和后缀的最长公共串的长度，
 * 也就是说，假如把 str 和 rev 拼接在一起变成 str + rev，假设长度为 2n
 * 那么对这个串运用 KMP 中计算查找数组的方法，就可以计算出一个 table
 * 其中 table[2n-1] 就是 str + rev 前缀和后缀的最长公共串的长度
 * 而且 str + rev 的前缀中一定包含 str 的前缀，str + rev 的后缀中一定包含 rev 的后缀
 * 因此 str + rev 的前后缀的最长公共串，一定是 str 的前缀和 rev 的后缀的最长公共串
 */
class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        char[] str = (s + '#' + rev).toCharArray();
        int[] f = new int[str.length];
        f[0] = 0;
        for (int i = 1; i < str.length; i++) {
            int t = f[i - 1];
            while (t > 0 && str[t] != str[i]) {
                t = f[t - 1];
            }
            if (str[i] == str[t]) {
                t++;
            }
            f[i] = t;
        }
        return rev.substring(0, s.length() - f[str.length - 1]) + s;
    }
}
