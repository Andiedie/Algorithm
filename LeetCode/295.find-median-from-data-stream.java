import java.util.Collections;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=295 lang=java
 *
 * [295] Find Median from Data Stream
 *
 * https://leetcode.com/problems/find-median-from-data-stream/description/
 *
 * algorithms
 * Hard (34.63%)
 * Total Accepted:    94.5K
 * Total Submissions: 269.9K
 * Testcase Example:  '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n[[],[1],[2],[],[3],[]]'
 *
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * For example,
 * 
 * [2,3,4], the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure.
 * double findMedian() - Return the median of all elements so far.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3) 
 * findMedian() -> 2
 * 
 * 
 * 
 * 
 * Follow up:
 * 
 * 
 * If all integer numbers from the stream are between 0 and 100, how would you
 * optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how
 * would you optimize it?
 * 
 * 
 */
/**
 * Use approach 3 in solution: Tow Heaps!
 * 按照题目要求，在数据流中寻找中位数。
 * 插入时耗时的方法：
 * 利用插入排序，维护数组的顺序
 * 查询时耗时的方法：
 * 查询时排序数组，取中位数
 * 
 * 但实际上我们不需要让整个数组都有序，只需要维护两个堆
 * 其中一个最大堆存比中位数小的数
 * 两个一个最小堆存比中位数大的数
 * 
 * 要维护这样的关系，只需要每个数到时，都将它插入最大堆
 * 然后从最大堆里拿出堆顶最大值插入最小堆
 * 如果此时两个堆的数量不平衡，就平衡一下
 * 
 * 此时两个堆堆顶的数据就可以用来计算中位数
 */
class MedianFinder {
    private PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> hi = new PriorityQueue<>();

    /** initialize your data structure here. */
    public MedianFinder() {
    }

    public void addNum(int num) {
        lo.add(num);
        hi.add(lo.poll());
        if (lo.size() < hi.size()) {
            lo.add(hi.poll());
        }
    }

    public double findMedian() {
        return lo.size() > hi.size() ? lo.peek() : (lo.peek() + hi.peek()) * 0.5;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
