/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 *
 * https://leetcode.com/problems/find-peak-element/description/
 *
 * algorithms
 * Medium (40.65%)
 * Total Accepted:    216.7K
 * Total Submissions: 532.5K
 * Testcase Example:  '[1,2,3,1]'
 *
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element
 * and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 * 
 * You may imagine that nums[-1] = nums[n] = -∞.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index
 * number 2.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5 
 * Explanation: Your function can return either index number 1 where the peak
 * element is 2, 
 * or index number 5 where the peak element is 6.
 * 
 * 
 * Note:
 * 
 * Your solution should be in logarithmic complexity.
 * 
 */
/**
 * Use Approach 2 in Solution: : Recursive Binary Search
 * Naive 的做法是直接从头到尾遍历一次数组，然后判断任何一个元素是不是比两边的元素大
 * 这里要注意的，数组两边 nums[-1] 和 nums[n] 默认是无限小的
 * 因此可以利用这个特点，因为第一个数字一定比左边（无限小）大，因此只需要比下一个大就行了
 * 而如果第二个数字比第一个大，那么第二个数字只需要比下一个大就行了
 * 总结一下就是，只需要比较一个数字是不是比后一个大就行了
 * 
 * 一种优化的方法是二分搜索
 * 朴素的二分搜索时，找到数组的中间元素
 * 如果就是要找的数字则直接返回
 * 如果要找的数字比中间元素小，就继续从左边搜索
 * 如果要找的数字比中间元素大，就继续从右边搜索
 * 
 * 这里可以用相同的原理
 * 找到数字的中间元素
 * 将中间元素与下一个元素比较，如果中间元素比较大，则表明在下坡
 * 如果下一个元素比较大，则表明在上坡
 * 如果在下坡，那么 peek 肯定在左边
 * 如果在上坡，那么 peek 肯定在右边
 * 直到某一次，搜索空间中只有一个元素，这个元素就是 peek
 */
class Solution {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    private int search(int[] nums, int left, int right) {
        if (left == right)
            return left;
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, left, mid);
        } else {
            return search(nums, mid + 1, right);
        }
    }
}
