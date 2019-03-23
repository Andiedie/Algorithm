/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 *
 * https://leetcode.com/problems/h-index/description/
 *
 * algorithms
 * Medium (34.40%)
 * Total Accepted:    117.3K
 * Total Submissions: 341K
 * Testcase Example:  '[3,0,6,1,5]'
 *
 * Given an array of citations (each citation is a non-negative integer) of a
 * researcher, write a function to compute the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index
 * h if h of his/her N papers have at least h citations each, and the other N −
 * h papers have no more than h citations each."
 * 
 * Example:
 * 
 * 
 * Input: citations = [3,0,6,1,5]
 * Output: 3 
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each
 * of them had 
 * ⁠            received 3, 0, 6, 1, 5 citations respectively. 
 * Since the researcher has 3 papers with at least 3 citations each and the
 * remaining 
 * two with no more than 3 citations each, her h-index is 3.
 * 
 * Note: If there are several possible values for h, the maximum one is taken
 * as the h-index.
 * 
 */
/**
 * H 指数：一个人在其所有学术文章中有 N 篇论文分别被引用了至少 N 次，他的 H 指数就是 N。
 * 一个计算方法是：
 * 1. 将所有的论文按引用次数降序排序
 * 2. 从前往后查找排序后的列表，知道某篇论文的序号大于该论文被引用的次数，所得序号减一就是 H 指数。
 * 
 * 以上方法的复杂度主要取决于排序，是 O(NlogN)
 * 利用桶排序，还可以将复杂度降到 O(N)
 * 
 * 观察几组输入：
 * [1000, 0]
 * [1000, 2, 0]
 * [1000, 4, 3, 0]
 * 
 * 可以发现，即使有若干篇文章的引用超多，但是 H 指数主要还是取决于，引用数和论文序号数相等的那一个位置
 * 因此我们可以设置 n + 1 个桶
 * 其中前 n 个桶，放引用介于 0 ~ n-1 的论文
 * 第 n + 1 个桶，放引用多与论文总数的论文
 * 这样计算时，可以直接跳过那些引用很多的论文，将注意力放在引用数和论文序号数接近的那几个论文上
 */
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 0)
            return 0;
        int[] buckets = new int[n + 1];
        for (int c : citations) {
            if (c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }
}
