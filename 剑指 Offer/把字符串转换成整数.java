/**
 * 将一个字符串转换成一个整数 (实现 Integer.valueOf(string) 的功能，但是 string 不符合数字要求时返回 0)
 * 要求不能使用字符串转换整数的库函数。 数值为 0 或者字符串不是一个合法的数值则返回 0。
 */
class Solution {
  public int StrToInt(String str) {
      int sign = 1;
      int ans = 0;
      char[] chars = str.toCharArray();
      for (int i = 0; i < chars.length; i++) {
          if (chars[i] >= '0' && chars[i] <= '9') {
              ans *= 10;
              ans += chars[i] - '0';
          } else {
              if (i == 0) {
                  if (chars[i] == '+') sign = 1;
                  else if (chars[i] == '-') sign = -1;
                  else return 0;
              } else {
                  return 0;
              }
          }
      }
      return ans * sign;
  }
}
