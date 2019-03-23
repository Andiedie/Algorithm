/**
 * 给定扑克牌上的数字 0 ~ 13，其中 0 表示大王或者小王
 * 大王和小王可以代表任何牌，输出该扑克牌序列是否为顺子
 * 
 * 如果是长度为 n 的顺子，必定满足：
 * 1. 不能有重复的牌
 * 2. 最大的牌 max 和最小的牌 min 之间的差值正好等于 n - 1
 * 3. 因为王的存在
 *    如果王替代了顺子中间的牌，max - min = n - 1
 *    如果王替代了最小的牌，max - min < n （此时 min 为比王替代的牌稍大一点的牌，因此和变小）
 *    如果王替代了最大的牌，max - min < n （此时 max 为比王替代的牌稍小一点的牌，因此和变小）
 * 
 * 综上，只需要确认每个牌只出现过一次，并且 max - min < n 即可
 */
class Solution {
  public boolean isContinuous(int[] numbers) {
      int n = numbers.length;
      if (n < 1) return false;
      int min = 14;
      int max = -1;
      boolean[] exists = new boolean[14];
      for (int num : numbers) {
          if (num == 0) continue;
          if (exists[num]) return false;
          exists[num] = true;
          max = Math.max(max, num);
          min = Math.min(min, num);
          if (max - min >= n) return false;
      }
      return true;
  }
}
