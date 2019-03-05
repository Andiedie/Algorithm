import java.util.Arrays;

/*
 * @lc app=leetcode id=279 lang=java
 *
 * [279] Perfect Squares
 *
 * https://leetcode.com/problems/perfect-squares/description/
 *
 * algorithms
 * Medium (40.62%)
 * Total Accepted:    163.5K
 * Total Submissions: 400.3K
 * Testcase Example:  '12'
 *
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 12
 * Output: 3 
 * Explanation: 12 = 4 + 4 + 4.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
/**
 * Use solution in https://leetcode.com/problems/perfect-squares/discuss/71488/Summary-of-4-different-solutions-(BFS-DP-static-DP-and-mathematics)
 * 动态规划
 * dp[i][j] 表示大小是 i 的数字，由前 j 的方形组成个数的最小值
 * 例如 dp[4][2] 表示数字 4 由 1、4 组成个数的最小值 = 1
 * dp[0][j] 和 dp[i][0] 显然都是 0
 * dp[i][j] 的值有两种情况
 * 1. 不使用第 j 个方形：dp[i][j-1]
 * 2. 使用第 j 个方形：dp[i - j*j][j-1] + 1
 * 因此 dp[i][j] = min(dp[i][j-1], dp[i - j*j][j-1] + 1)
 * 由于每次更新 dp 只需要上一次的值，所以优化空间复杂度为一维矩阵
 * dp[i] = min(dp[i], dp[i - j*j] + 1)
 */
class Solution {
    public int numSquares(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n];
    }
}

