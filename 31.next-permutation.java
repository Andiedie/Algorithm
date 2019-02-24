/*
 * @lc app=leetcode id=31 lang=java
 *
 * [31] Next Permutation
 *
 * https://leetcode.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (30.00%)
 * Total Accepted:    212.3K
 * Total Submissions: 707.4K
 * Testcase Example:  '[1,2,3]'
 *
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place and use only constant extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 */
/**
 * Use Approach 2 in Solution: Single Pass Approach
 * 首先必须确定，一个完全倒序的数组，不可能有下一个排列。
 * 从题意也可以知晓，完全倒序的数组的下一个排列就是 第一个排列（完全正序）
 * 假定目前的数组还有下一个排列，那么数组中一定存在有两个连续的数，a[i] 和 a[i + 1]
 * 满足 a[i] < a[i + 1]，即正序
 * 我们可以从头开始找，也可以从后往前找
 * 但是因为我们要找的是最小的下一个排列，必须从后往前找。
 * 找到 a[i] 后，有如下性质：
 * - a[i] 的右边的所有数都是倒序的
 * - a[i] 右边的数字，一定存在比 a[i] 大的（a[i + 1] 就比 a[i] 大）
 * 
 * 思考，当我们手动进行排列时，为了产生下一个排列，我们的方法是选一个正好比当前元素大一点的值，和当前互换
 * 同理，为了生成题目给定数组的下一个排列，就必须使用一个正好比 a[i] 大一点点的值替换 a[i]
 * 而这个数，一定在 a[i] 的右边，假定是 a[j]
 * 所以我们只需要从 a[i] 右边找到这个数，和 a[i] 互换就行了
 * 
 * 但是还没有完，让我们接着思考一个递归问题
 * 排列其实是一个递归的过程，当我们确定一个高位的值后
 * 接下来的排列，其实就是高位的值 与 剩下位排列 的组合
 * 也就是说，当一个高位确定下来之后，他的下一个排列一定是剩下排列的初始情况
 * 而这个初始情况就是升序
 * 
 * 回到题目，刚刚我们 a[j] 这个刚好比 a[i] 大一点点的值放在了高位
 * 那么下一个排列时，高位右边的值一定得是升序的
 * 恰好，根据之前总结的性质，右边的值是倒序的
 * 虽然我们使用了 a[j] 和 a[i] 互换，但是由于 a[j] 是倒序数组中，离 a[i] 最近的值
 * 这样的互换不会造成倒序改变，所以只需要颠倒右边的数组即可
 * 
 * 总结步骤：
 * 1. 找到两个连续的数 a[i] < a[i + 1]
 * 2. 在 a[i] 右边找一个恰好比 a[i] 大一点点的数 a[j]
 * 3. a[i] 和 a[j] 的值互换
 * 4. 将 a[i] 右边的数组颠倒，使之升序
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int target = nums.length - 2;
        while (target >= 0 && nums[target + 1] <= nums[target]) target--;
        if (target >= 0) {
            int justBiggerIndex = nums.length - 1;
            while (justBiggerIndex >= 0 && nums[justBiggerIndex] <= nums[target]) justBiggerIndex--;
            swap(nums, target, justBiggerIndex);
        }
        reverse(nums, target + 1, nums.length - 1);
    }
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private void reverse(int[] a, int start, int end) {
        int i = start, j = end;
        while (i < j) swap(a, i++, j--);
    }
}
