/**
 * 在跳向第 n 个台阶时，会有两种情况
 * 从 n - 1 个台阶跳一层上来，共有 f(n - 1) 种情况
 * 从 n - 2 个台阶跳两层上来，共有 f(n - 2) 种情况
 * 合集 f(n - 1) + f(n - 2)
 * 
 * 问题转为求斐波那契数
 */
class Solution {
  public int JumpFloor(int target) {
      // 第 0 个台阶 1 中跳法，第一个台阶 1 中跳法
      return f(1, 1, target);
  }
  private int f(int cur, int next, int n) {
      if (n == 0) return cur;
      return f(next, cur + next, n - 1);
  }
}
