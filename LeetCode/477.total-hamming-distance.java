/*
 * @lc app=leetcode id=477 lang=java
 *
 * [477] Total Hamming Distance
 *
 * https://leetcode.com/problems/total-hamming-distance/description/
 *
 * algorithms
 * Medium (48.45%)
 * Total Accepted:    44.1K
 * Total Submissions: 90.8K
 * Testcase Example:  '[4,14,2]'
 *
 * The Hamming distance between two integers is the number of positions at
 * which the corresponding bits are different.
 * 
 * Now your job is to find the total Hamming distance between all pairs of the
 * given numbers.
 * 
 * 
 * Example:
 * 
 * Input: 4, 14, 2
 * 
 * Output: 6
 * 
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is
 * 0010 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2
 * + 2 + 2 = 6.
 * 
 * 
 * 
 * Note:
 * 
 * Elements of the given array are in the range of 0  to 10^9
 * Length of the array will not exceed 10^4. 
 * 
 * 
 */
/**
 * Use solution in https://leetcode.com/problems/total-hamming-distance/discuss/96226/Java-O(n)-time-O(1)-Space
 * 首先对于任何一对数字，汉明距离就是两者的异或后的二进制表示中 1 的个数
 * Hamming(a, b) = Integer.bitCount(a ^ B)
 * 
 * 朴素的解法是两两计算后累加 o(n^2)
 * 
 * 这里可以利用二进制的性质
 * 首先统计某一个位，所有数字在这一位为 1 的数量
 * 假设共有 n 个数，且 p 个数在这某一个位是 1
 * 那么就有 n - p 个数在这一位为 0
 * 那么这一位给总体汉明距离带来了 p * (n - p) 的增益
 * 可以认为，从 p 个数里选一个，(n - p) 个数里选一个
 * 任意的排列都会给总体汉明距离 +1
 */
class Solution {
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j = 0; j < 32; j++) {
            int bitCount = 0;
            for (int i = 0; i < n; i++) {
                bitCount += (nums[i] >> j) & 1;
            }
            total += bitCount * (n - bitCount);
        }
        return total;
    }
}

