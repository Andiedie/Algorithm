/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为 9 的数组 {1,2,3,2,2,2,5,4,2}。
 * 由于数字 2 在数组中出现了 5 次，超过数组长度的一半，因此输出 2。如果不存在则输出 0。
 * 
 * 使用投票的方法
 * 选择第一个数字作为候选人，得票 1
 * 之后的每一个数字，如果和候选人相同，则得票 +1，不同则得票 -1
 * 
 * 如果某一个候选人的得票变为了 0，则将当前数字推选为新的候选人，得票 1
 * 
 * 这样可以选出数组中出现次数最多的数字
 * 
 * 在进行一次循环，确认这个数字的出现次数大于 n / 2即可
 */
class Solution {
  public int MoreThanHalfNum_Solution(int [] array) {
      int n = array.length;
      if (n == 0) return 0;
      int cur = array[0];
      int count = 1;
      for (int i = 0; i < n; i++) {
          if (array[i] == cur) {
              count++;
          } else {
              count--;
              if (count == 0) {
                  count = 1;
                  cur = array[i];
              }
          }
      }
      count = 0;
      for (int i : array) {
          if (i == cur) count++;
      }
      if (count > n / 2) return cur;
      return 0;
  }
}
