/*
 * @lc app=leetcode id=66 lang=java
 *
 * [66] Plus One
 *
 * https://leetcode.com/problems/plus-one/description/
 *
 * algorithms
 * Easy (40.60%)
 * Total Accepted:    351.2K
 * Total Submissions: 864.2K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a non-empty array of digits representing a non-negative integer, plus
 * one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of
 * the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the
 * number 0 itself.
 *
 * Example 1:
 *
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 *
 *
 * Example 2:
 *
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 *
 *
 */
/**
 * 给定一个非空数组，数字内的所有数字组成了一个非负的整数
 * 给这个整数 +1
 */
class Solution {
    public int[] plusOne(int[] digits) {
        int[] ans = digits;
        ans[ans.length - 1]++;
        for (int i = ans.length - 1; i > 0; i--) {
            if (ans[i] >= 10) {
                ans[i - 1]++;
                ans[i] -= 10;
            }
        }
        if (ans[0] >= 10) {
            ans = new int[ans.length + 1];
            System.arraycopy(digits, 0, ans, 1, digits.length);
            ans[0]++;
            ans[1] -= 10;
        }
        return ans;
    }
}
