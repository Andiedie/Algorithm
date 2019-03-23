import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 *
 * https://leetcode.com/problems/word-break-ii/description/
 *
 * algorithms
 * Hard (26.57%)
 * Total Accepted:    147K
 * Total Submissions: 552.5K
 * Testcase Example:  '"catsanddog"\n["cat","cats","and","sand","dog"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is
 * a valid dictionary word. Return all such possible sentences.
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
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 * 
 */
/**
 * Use solution in https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS/215095
 * 深度优先搜索
 * 首先，从开头寻找一个词典中有的词语
 * 然后对于剩余的子串，接着这样寻找，将所有寻找的结果拼起来，就是一种分词结果
 * 接着从开头寻找词典中有的下一个词语，重复上述步骤
 * 
 * 这里有几个可以优化的地方：
 * 1. 确认一个词语是否在在词典中，可以用 Set 加速
 * 2. 使用 Map 存储已经计算的结果，避免重复计算
 * 3. 记录词典中最长的词语的长度，对于比这个还长的词语，可以直接跳过
 */
class Solution {
    private Set<String> dict;
    private Map<Integer, List<String>> store;
    private int maxLength = 0;
    private String str;

    public List<String> wordBreak(String s, List<String> wordDict) {
        str = s;
        dict = new HashSet<>(wordDict);
        store = new HashMap<>();
        for (String word : wordDict)
            maxLength = Math.max(maxLength, word.length());
        return DFS(0);
    }

    private List<String> DFS(int start) {
        if (store.containsKey(start))
            return store.get(start);
        List<String> res = new LinkedList<>();
        if (start == str.length())
            res.add(null);
        for (int i = start; i < start + maxLength && i < str.length(); i++) {
            String substr = str.substring(start, i + 1);
            if (dict.contains(substr)) {
                List<String> sublist = DFS(i + 1);
                for (String sub : sublist) {
                    if (sub == null) {
                        res.add(substr);
                    } else {
                        res.add(substr + " " + sub);
                    }
                }
            }
        }
        store.put(start, res);
        return res;
    }
}
