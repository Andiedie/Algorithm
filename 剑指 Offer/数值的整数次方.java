/**
 * 快速幂
 */
class Solution {
  public double Power(double base, int exponent) {
      // 递归跳出条件
      if (exponent == 0) return 1;
      // 防止特殊溢出情况
      if (exponent == Integer.MIN_VALUE) return Power(base * base, exponent / 2);
      // 处理负指数
      if (exponent < 0) {
          exponent = -exponent;
          base = 1 / base;
      }
      // 尾递归
      // 处理奇偶数次幂
      return exponent % 2 == 0 ? Power(base * base, exponent / 2) : base * Power(base * base, exponent / 2);
  }
}