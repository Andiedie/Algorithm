/*
 * @lc app=leetcode id=231 lang=java
 *
 * [231] Power of Two
 *
 * https://leetcode.com/problems/power-of-two/description/
 *
 * algorithms
 * Easy (41.58%)
 * Total Accepted:    213.5K
 * Total Submissions: 512.8K
 * Testcase Example:  '1'
 *
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 *
 *
 * Input: 1
 * Output: true
 * Explanation: 2^0 = 1
 *
 *
 * Example 2:
 *
 *
 * Input: 16
 * Output: true
 * Explanation: 2^4 = 16
 *
 * Example 3:
 *
 *
 * Input: 218
 * Output: false
 *
 */
/**
 * 给定一个整数，判断这个数是不是 2 的倍数
 *
 * Use solution in https://leetcode.com/problems/power-of-two/discuss/63966/4-different-ways-to-solve-Iterative-Recursive-Bit-operation-Math
 * 最简单的方法是直接数一下二进制位中 1 的个数
 *
 * 也可以使用 (n & (n - 1)) == 0 的方法
 * 因为 2 的幂次一定只有一位是 1，把这个数 -1 之后
 * 原来是 1 的那一位变成 0，之后的所有位变成 1
 * 这两者的按位与必然是 0
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
        // n > 0 && Integer.bitCount(n) == 1;
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
