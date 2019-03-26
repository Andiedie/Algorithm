import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 *
 * https://leetcode.com/problems/longest-consecutive-sequence/description/
 *
 * algorithms
 * Hard (40.78%)
 * Total Accepted:    189.9K
 * Total Submissions: 465.2K
 * Testcase Example:  '[100,4,200,1,3,2]'
 *
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 *
 *
 */
/**
 * 给定一个无序数组，找到数组中最长连续递增序列的长度
 *
 * Use approach 3 in Solution: HashSet and Intelligent Sequence Building
 * 首先从暴力搜索入手
 * 对于数组中的每一个数，都计算以它为开头的最长子串。
 * 如何计算：
 * 测试比这个数大 1 的数在不在数组中，在的话就继续搜索。
 *
 * 这里对于数组中的每一个数要计算一遍；
 * 对于比这个数大 1 的每一个数要确认一遍是否在数组；
 * 每次确认都需要遍历一次数组，
 * 综上，暴力搜索时间复杂度为 O(n^3)
 *
 * 现在开始优化：
 * 最简单的，确认一个数是否在数组中，可以使用 Set 来快速解决
 * 然后，其实我们并不需要为每一个数都计算以它为起始的最长子串
 * 试想，一个数如果已经存在在某个子串中，那么以它为起始的最长子串必然不是全局最长的
 * 所以如果一个数已经出现在了另个一个数的子串中，就可以不计算它
 * 如果判断一个数是不是已经存在于另一个数的子串中了呢？
 * 只要这个数的先驱不存在，那么它就一定是起始数字；如果先驱存在，它一定在先驱的子串中。
 * 巧的是，确定先驱存不存在也可以借由 Set 完成。
 *
 * 因此优化了两重循环，最终复杂度为 O(n)
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int longestStreak = 0;
        for (int num : nums)
            set.add(num);
        for (int num : set) {
            if (set.contains(num - 1))
                continue;
            int currentStreak = 1;
            while (set.contains(++num)) {
                currentStreak += 1;
            }
            longestStreak = Math.max(longestStreak, currentStreak);
        }
        return longestStreak;
    }
}
