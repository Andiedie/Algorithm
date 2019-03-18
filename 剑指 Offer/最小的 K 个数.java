import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 输入 n 个整数，找出其中最小的 K 个数。例如输入 4,5,1,6,2,7,3,8 这 8 个数字，则最小的 4 个数字是 1,2,3,4。
 * 
 * 两个解法：
 * 1. 使用一个大小为 k 的最大堆，将所有数字与堆顶比较，如果比堆顶小就删除堆顶并加入新的数
 * 2. 类似 TopK 的 Partition 解法
 */
class Solution {
  public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
      if (k == 0 || k > input.length) return new ArrayList<>();
      PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
      for (int i = 0; i < k; i++) {
          q.add(input[i]);
      }
      for (int i = k; i < input.length; i++) {
          if (input[i] < q.peek()) {
              q.poll();
              q.add(input[i]);
          }
      }
      return new ArrayList<>(q);
  }
}
