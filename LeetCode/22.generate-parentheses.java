import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 *
 * https://leetcode.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (52.95%)
 * Total Accepted:    299.2K
 * Total Submissions: 564.3K
 * Testcase Example:  '3'
 *
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * 
 * 
 * For example, given n = 3, a solution set is:
 * 
 * 
 * [
 * ⁠ "((()))",
 * ⁠ "(()())",
 * ⁠ "(())()",
 * ⁠ "()(())",
 * ⁠ "()()()"
 * ]
 * 
 */
/**
 * Use approach 2 in Solution: Backtracking
 * 回溯法解决，在暴力搜索的基础上，使用计数器剪枝
 */
class Solution {
    private List<String> answers;

    public List<String> generateParenthesis(int n) {
        answers = new ArrayList<>();
        generate("", 0, 0, n);
        return answers;
    }

    private void generate(String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            answers.add(current);
            return;
        }
        if (open < max) {
            generate(current + "(", open + 1, close, max);
        }
        // close >= open 时非法
        if (close < open) {
            generate(current + ")", open, close + 1, max);
        }
    }
}
