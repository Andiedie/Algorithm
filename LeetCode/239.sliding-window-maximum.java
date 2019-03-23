import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=239 lang=java
 *
 * [239] Sliding Window Maximum
 *
 * https://leetcode.com/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (36.94%)
 * Total Accepted:    138.8K
 * Total Submissions: 373.8K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Return the max sliding window.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7] 
 * Explanation: 
 * 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty
 * array.
 * 
 * Follow up:
 * Could you solve it in linear time?
 * 
 */
/**
 * Use solution in https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
 * 扫描数组，并使用一个双向队列，记录当前最大值的指针
 * 这个双向队列中，我们只保证队列头部的指针一定指向当前窗口的最大元素即可
 * 每次扫描时进行如下操作：
 * 1. 如果队列头部的元素已经超过了窗口范围，删除它
 * 2. 如果新加入的元素比队列尾部的元素大，那么将它冒泡到合适的位置，并将比它小的元素删除
 * 3. 取队列头部的元素，这个元素就是当前窗口的最大元素
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0)
            return new int[0];
        int[] ans = new int[nums.length - k + 1];
        int ansIndex = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            int rightBound = i - k + 1;
            // remove numbers out of range k
            if (!q.isEmpty() && q.peekFirst() < rightBound) {
                q.pollFirst();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.addLast(i);
            if (i >= k - 1) {
                ans[ansIndex++] = nums[q.peekFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new Solution().maxSlidingWindow(new int[] { 1, 3, -1, -1, 5, 3, 6 }, 3);
    }
}
