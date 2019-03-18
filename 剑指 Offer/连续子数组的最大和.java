/**
 * 给定一个整数数组，求数组中最大连续子序列的和
 * 例如 {6,-3,-2,7,-15,1,2,2}, 连续子序列的最大和为 8(从第 0 个开始, 到第 3 个为止)
 * 
 * 使用动态规划
 * dp[i] 表示 i 结尾时连续子序列的最大和
 * dp[0] = array[0]
 * dp[i] = max(dp[i - 1] + array[i], array[i])
 * 优化空间复杂度 dp = max(dp + array[i], array[i])
 */
class Solution {
  public int FindGreatestSumOfSubArray(int[] array) {
      int dp = array[0];
      int ans = dp;
      for (int i = 1; i < array.length; i++) {
          dp = Math.max(dp + array[i], array[i]);
          ans = Math.max(dp, ans);
      }
      return ans;
  }
}
