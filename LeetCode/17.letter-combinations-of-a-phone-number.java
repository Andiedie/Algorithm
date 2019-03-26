import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (40.22%)
 * Total Accepted:    344.3K
 * Total Submissions: 855.1K
 * Testcase Example:  '"23"'
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 *
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 *
 */
/**
 * 给定一串数字，每个数字都可以表示九键盘中一个按钮
 * 返回所有的字母组合情况
 *
 * Use Approach 1 in Solution: Backtracking
 * 回溯法
 * 使用递归扫描所有可能
 */
class Solution {
    private static char[][] table = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
            { 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };
    List<String> output = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }

    private void backtrack(String combination, String remainDigits) {
        if (remainDigits.length() == 0) {
            output.add(combination);
            return;
        }
        int num = remainDigits.charAt(0) - '0';
        char[] chs = table[num];
        for (char c : chs) {
            backtrack(combination + c, remainDigits.substring(1));
        }
    }
}
