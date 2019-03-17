/**
 * 使用尾递归优化
 */
class Solution {
  public int Fibonacci(int n) {
      if (n < 2) return n;
      return f(0, 1, n);
  }
  private int f(int cur, int next, int n) {
      if (n == 0) return cur;
      return f(next, cur + next, n - 1);
  }
}
