/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (25.43%)
 * Total Accepted:    380.4K
 * Total Submissions: 1.5M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * You may assume nums1 and nums2 cannot be both empty.
 * 
 * Example 1:
 * 
 * 
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * 
 * 
 * Example 2:
 * 
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 * 
 * 
 */
/**
 * Use Approach 1 in Solution: Recursive Approach
 * 首先理解什么是中位数：中位数是指，讲一个数组分成等长的两个部分，且左边部分一定比右边的小
 * 我们将 A 分割成两部分，得到两个部分 left_A 和 right_A
 * 假设 A 有 m 个元素，则一定有 m + 1 种分割方法
 * 且 len(left_A) = i, len(right_A) = m - i，其中 i ∈ [0, m]
 * 使用相同的方法将 B 分割为 left_B 和 right_B, len(left_B) = j, len(right_B) = n - j, j ∈ [0, n]
 * 现在我们将 left_A, left_B 放在一起组成 left_part, right_A 和 right_B 放在一起组成 right_part
 * 假如我们可以保证：
 * 1. len(left_part)=len(right_part)
 * 2. max(left_part)≤min(right_part)
 * 那么可以认为，此时 A, B 的分割方法就是中位数分割
 * 中位数 = [max(left_part)+min(right_part)] / 2
 * 
 * 要使上面的两个条件成立，就必须保证：
 * 1. i+j=m−i+n−j 即 j = (m+n)/2 - i, 其中 i ∈ [0, m] 且 n ≥ m (保证 j ≥ 0)
 * 2. A[i-1] ≤ B[j] 且 B[j-1] ≤ A[i]
 * 这里要注意边界情况：
 * - 如果 i = 0 或 i = m, 只需要检查 B[j-1] ≤ A[i] 即可
 * - 如果 j = 0 或 j = n，只需要检查 A[i-1] ≤ B[j] 即可
 * 
 * 现在要做的事情确定了：遍历 i ∈ [0, m], j = (m+n)/2 - i, 使得 A[i-1] ≤ B[j] 且 B[j-1] ≤ A[i]
 * 
 * 可以通过二分查找实现：
 * 1. imin = 0, imax = m, 开始的搜索区间是 [imin, imax]
 * 2. i = (imin + imax) / 2, j = (m+n)/2 - i
 * 3. 为了确保 A[i-1] ≤ B[j] 且 B[j-1] ≤ A[i]，会出现三种情况：
 *  - 条件满足，停止搜索
 *  - A[i-1] > B[j]，说明 i 太大了，继续在 [imin, i - 1] 区间搜索
 *  - B[j-1] > A[i] 说明 i 太小了，继续在 [i + 1, imax] 区间搜索
 * 4. 找到 i 后，求中位数：
 *  - 如果 m + n 是奇数，max(A[i−1],B[j−1])
 *  - 如果 m + n 是偶数，max(A[i−1],B[j−1])+min(A[i],B[j]) / 2
 */
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
