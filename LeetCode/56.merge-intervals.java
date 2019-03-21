import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 *
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (34.70%)
 * Total Accepted:    304.2K
 * Total Submissions: 875.4K
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into
 * [1,6].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 */
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
/**
 * Use approach 2 in Solution: Sorting
 * 先对所有 intervals 排序
 * 然后拼接所有可以拼接的
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
        if (intervals.size() == 0) return ans;
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        Interval first = intervals.get(0);
        ans.add(new Interval(first.start, first.end));
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (ans.isEmpty()) {
                ans.add(current);
                continue;
            }
            Interval last = ans.get(ans.size() - 1);
            if (last.end < current.start) {
                ans.add(current);
            } else {
                last.end = Math.max(last.end, current.end);
            }

        }
        return ans;
    }
}
