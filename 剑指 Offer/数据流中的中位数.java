import java.util.Collections;
import java.util.PriorityQueue;
/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用 Insert() 方法读取数据流，使用 GetMedian() 方法获取当前读取数据的中位数。
 * 
 * 可以利用 AVL 树的修改版，原版的 AVL 树是根据节点的平衡因子来调节平衡
 * 这里改为左右节点的树高来调节平衡
 * 如果左右子树的树高相同，则直接返回根节点的值
 * 如果左右子树的树高不同，则从左右子树中选出一个较高的树，找到树中的最大（左子树）或最小（右子树）值即可
 * 
 * 另一种方法是通过维护一个最大堆和一个最小堆
 * 可以认为，最大堆的每一个数字都都小于等于中位数
 * 最小堆中的每一个数字都大于等于中位数
 * 那么返回中位数的时候，如果最小堆比较大，意味着有数据流中只有奇数个数字，直接返回最小堆中的数字
 * 如果一样大，则去最小堆的和最大堆中的头部，算平均
 * 
 * 那么如何维护上述关系呢
 * 只需要每次读入数字都放入最小堆，然后从最小堆中取出最小值放入最大堆
 * 如果此时两个堆不平衡，则进行平衡操作
 */
class Solution {
    private PriorityQueue<Integer> hi = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> lo = new PriorityQueue<>();
    public void Insert(Integer num) {
        lo.add(num);
        hi.add(lo.poll());
        if (lo.size() < hi.size()) {
            lo.add(hi.poll());
        }
    }

    public Double GetMedian() {
        return lo.size() > hi.size() ? (double)lo.peek() : (lo.peek() + hi.peek()) * 0.5;
    }
}
