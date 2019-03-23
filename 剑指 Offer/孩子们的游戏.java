/**
 * 首先, 让小朋友们围成一个大圈。
 * 然后, 他随机指定一个数 m, 让编号为 0 的小朋友开始报数。
 * 每次喊到 m-1 的那个小朋友要出列，并且不再回到圈中,
 * 从他的下一个小朋友开始, 继续 0...m-1 报数，直到剩下最后一个小朋友
 * 求最后一个小朋友的编号
 *
 * 这是一个约瑟夫环的问题，
 * 可以使用链表进行模拟
 * 最好的情况是一个单向成环的链表，这里直接使用了 Java 内置的链表
 * 每次都删掉上次开始位置 start 加上跳跃次数 m - 1 位置上的数
 * 最后剩下的就是答案
 *
 * 如果求编号顺序则可以使用上述方案，如果只求最后一个孩子的编号，则可以用数学归纳法
 * f(n, m) = (f(n - 1, m) + m) % n
 * 其中 f(n, m) 表示一共有 n 个数字成环，每次报数 m 个时，最后一个编号是什么
 * 已知，f(1, m) = 0
 *
 * 形式上类似于动态规划，看似需要 O(n) 的空间
 * 然而因为每次只需要上一次的结果，因此可以直接优化成 O(1)
 */

// import java.util.LinkedList;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;

// class Solution {
//     public int LastRemaining_Solution(int n, int m) {
//         LinkedList<Integer> list = new LinkedList<>(IntStream.range(0, n).boxed().collect(Collectors.toList()));
//         if (list.size() < 1)
//             return -1;
//         int start = 0;
//         while (list.size() > 1) {
//             start = (start + m - 1) % list.size();
//             list.remove(start);
//         }
//         return list.get(0);
//     }
// }

class Solution {
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1)
            return -1;
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
