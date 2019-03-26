import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 *
 * https://leetcode.com/problems/the-skyline-problem/description/
 *
 * algorithms
 * Hard (30.96%)
 * Total Accepted:    85.3K
 * Total Submissions: 274.7K
 * Testcase Example:  '[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]'
 *
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are
 * given the locations and height of all the buildings as shown on a cityscape
 * photo (Figure A), write a program to output the skyline formed by these
 * buildings collectively (Figure B).
 * ⁠
 *
 * The geometric information of each building is represented by a triplet of
 * integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and
 * right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You
 * may assume all buildings are perfect rectangles grounded on an absolutely
 * flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as: [
 * [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key
 * point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the
 * termination of the skyline, and always has zero height. Also, the ground in
 * between any two adjacent buildings should be considered part of the skyline
 * contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3
 * 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 *
 * The number of buildings in any input list is guaranteed to be in the range
 * [0, 10000].
 * The input list is already sorted in ascending order by the left x position
 * Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output
 * skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not
 * acceptable; the three lines of height 5 should be merged into one in the
 * final output as such: [...[2 3], [4 5], [12 7], ...]
 *
 *
 */
/**
 * 输入每栋大楼的起点，终点和高度
 * 输出城市的天际线
 *
 * Use solution in https://leetcode.com/problems/the-skyline-problem/discuss/61281/Java-divide-and-conquer-solution-beats-96
 * 有一个朴素的方法是使用高度地图
 * 每处理一个大楼，就将大楼占地的地方设为其高度（如果已有高度，则取最大值）
 * 最后根据高度地图返回答案即可
 * 类似于一个一个大楼拔地而起，逐渐构建天际线的过程
 * 本质上是模拟，主要的问题是空间占用率太高
 *
 * 这里使用的方法是分治法
 * 下面的写法是 Top-Down
 * 这里解释时使用 Bottom-Up 比较容易理解
 * 首先是单栋大楼，天际线就是自己，因此答案是两个点，一个是大楼的左上角，一个是右下角
 * 对于多个点合并，请看代码更容易理解
 */
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0)
            return res;
        int n = buildings.length;
        return findSkyline(buildings, 0, n - 1);
    }

    private LinkedList<int[]> findSkyline(int[][] buildings, int lo, int hi) {
        LinkedList<int[]> res = new LinkedList<>();
        if (lo > hi)
            return res;
        if (lo == hi) {
            res.add(new int[] { buildings[lo][0], buildings[lo][2] });
            res.add(new int[] { buildings[lo][1], 0 });
            return res;
        }
        int mid = lo + (hi - lo) / 2;
        LinkedList<int[]> left = findSkyline(buildings, lo, mid);
        LinkedList<int[]> right = findSkyline(buildings, mid + 1, hi);
        int leftH = 0, rightH = 0;

        while (!left.isEmpty() && !right.isEmpty()) {
            int x1 = left.peekFirst()[0];
            int x2 = right.peekFirst()[0];
            int x = Math.min(x1, x2);
            if (x1 < x2) {
                leftH = left.pollFirst()[1];
            } else if (x1 > x2) {
                rightH = right.pollFirst()[1];
            } else {
                leftH = left.pollFirst()[1];
                rightH = right.pollFirst()[1];
            }
            int h = Math.max(leftH, rightH);
            if (res.isEmpty() || h != res.peekLast()[1]) {
                res.add(new int[] { x, h });
            }
        }
        res.addAll(left);
        res.addAll(right);
        return res;
    }
}
