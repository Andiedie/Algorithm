/**
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含 0 次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串 "aaa" 与模式 "a.a" 和 "ab*ac*a" 匹配，但是与 "aa.a" 和 "ab*a" 均不匹配
 * 
 * 使用递归求解，并缓存中间结果避免重复运算
 * 实测缓存结果 LeetCode 中可以将时间从 33ms 降低到 11ms
 * 在牛客网可以将时间从 19ms 降低到 16ms
 */
class Solution {
    Boolean[][] dp;

    public boolean match(char[] str, char[] pattern) {
        dp = new Boolean[str.length + 1][pattern.length + 1];
        return isMatch(0, 0, str, pattern);
    }

    private boolean isMatch(int sIndex, int pIndex, char[] s, char[] p) {
        // 有缓存结果，直接返回
        if (dp[sIndex][pIndex] != null)
            return dp[sIndex][pIndex];
        boolean ans;
        // 如果已经到达了模式串的末尾
        if (pIndex == p.length) {
            // 是否匹配决定于匹配串是否也到了末尾（如果不要求完全匹配，这里可以直接返回 true）
            ans = sIndex == s.length;
        } else {
            // 计算模式串的第一个字符和匹配串的第一个字符是否匹配
            boolean firstMatch = (
            // 匹配串必须还有字符
            sIndex < s.length &&
            // 匹配串和模式串的字符相等或者模式串第一个字符是点 .
                    (s[sIndex] == p[pIndex] || p[pIndex] == '.'));
            // 如果下一个字符是 *
            if (pIndex + 1 < p.length && p[pIndex + 1] == '*') {
                // 要么不匹配任何字符，跳过 *
                ans = isMatch(sIndex, pIndex + 2, s, p) ||
                // 要么在当前已经匹配一个字符的情况下，继续匹配下去
                        (firstMatch && isMatch(sIndex + 1, pIndex, s, p));
            } else {
                // 下一个字符不是 *，继续匹配
                ans = firstMatch && isMatch(sIndex + 1, pIndex + 1, s, p);
            }
        }
        // 缓存结果
        dp[sIndex][pIndex] = ans;
        return ans;
    }
}
