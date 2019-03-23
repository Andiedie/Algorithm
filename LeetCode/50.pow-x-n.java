/*
 * @lc app=leetcode id=50 lang=java
 *
 * [50] Pow(x, n)
 *
 * https://leetcode.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (27.48%)
 * Total Accepted:    288.4K
 * Total Submissions: 1M
 * Testcase Example:  '2.00000\n10'
 *
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * 
 * Example 1:
 * 
 * 
 * Input: 2.00000, 10
 * Output: 1024.00000
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 2.10000, 3
 * Output: 9.26100
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * 
 * 
 * Note:
 * 
 * 
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 * 
 * 
 */
/**
 * 快速幂
 */
class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        // 如果当前值是 -2147483648，即 32 位 int 的最小值
        // 那么下面的符号反转会导致移除
        // -(-2147483648) = 2147483648 > 2147483647 (32 位 int 最大值)
        // 所以先进行一次普通乘法
        if (n == Integer.MIN_VALUE)
            return myPow(x * x, n / 2);
        // 如果指数是负数，就转换成分数的正指数
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().myPow(2, -2147483648));
    }
}
