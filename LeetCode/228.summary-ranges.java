import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=228 lang=java
 *
 * [228] Summary Ranges
 *
 * https://leetcode.com/problems/summary-ranges/description/
 *
 * algorithms
 * Medium (35.07%)
 * Total Accepted:    124.4K
 * Total Submissions: 353.5K
 * Testcase Example:  '[0,1,2,4,5,7]'
 *
 * Given a sorted integer array without duplicates, return the summary of its
 * ranges.
 *
 * Example 1:
 *
 *
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 *
 *
 * Example 2:
 *
 *
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 *
 *
 */
/**
 * 输入一个升序整数数组，返回对这个数组的范围概括
 */
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int start = i;
            int end = i;
            while (end + 1 < nums.length && nums[end] + 1 == nums[end + 1]) {
                end++;
            }
            if (start == end) {
                ans.add(String.valueOf(nums[start]));
            } else {
                ans.add(nums[start] + "->" + nums[end]);
            }
            i = end;
        }
        return ans;
    }
}
