import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=139 lang=java
 *
 * [139] Word Break
 *
 * https://leetcode.com/problems/word-break/description/
 *
 * algorithms
 * Medium (34.20%)
 * Total Accepted:    301K
 * Total Submissions: 878.5K
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet
 * code".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 
 * 
 */
/**
 * Use solution in https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways
 * 动态规划
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 记录“在 i 位置之前，是否有一个合法单词已经结尾”
        boolean[] isWordEndsBefore = new boolean[s.length() + 1];
        // 在第一个字符之前，字符串是空的，自然是一个合法的单词（空单词）
        isWordEndsBefore[0] = true;
        // 加速查找
        Set<String> set = new HashSet<>(wordDict);
        // 记录字典里的最长单词，这样所有比这个单词长的单词都可以直接认为是不合法的
        int maxLength = 0;
        for (String ss : wordDict)
            maxLength = Math.max(maxLength, ss.length());
        // 对于字符串中的每一个位置
        for (int i = 0; i < s.length(); i++) {
            // 如果之前已经有合法单词结尾
            if (isWordEndsBefore[i]) {
                // 之后的每一个前缀都尝试是不是合法的，是的话就记录下来
                for (int j = i + 1; j <= s.length(); j++) {
                    if (j - i <= maxLength && set.contains(s.substring(i, j))) {
                        isWordEndsBefore[j] = true;
                    }
                }
            }
            // 之前没有合法单词结尾，那么这里就没有资格继续尝试下去
        }
        // 字符串的最后一个位置如果是以合法单词结尾的，那么整体也一定是
        return isWordEndsBefore[s.length()];
    }
}
