import java.util.Stack;

/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (41.71%)
 * Total Accepted:    254.6K
 * Total Submissions: 609.7K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 *
 * Example:
 *
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 */
/**
 * 给定 n 个，每个数字代表对应高度的墙
 * 求出，下雨后，最多能存储多少水
 *
 * Use Approach 3 in Solution: Using stacks
 * 使用一个栈记录之前遇到的所有墙
 * 当遇到一个新的墙时，如果这个墙比之前的矮，直接加入栈中，这表示：这堵墙被上一堵墙围住了
 * 如果新的墙壁上一堵墙高，那么新的墙也围住了上一堵墙，话句话说，上一堵墙被两堵墙围住了，可以装水了
 * 再从栈中取出来另一个围堵的墙，两墙之间的距离 * （两墙最低高度 - 存水墙的高度），就是存水量
 *
 * 还没完，我们可以用新墙继续和栈里面的墙比较下去，很有可能这堵新墙很高，可以和很多以前的墙形成新的水塘
 * 栈空了，或者遇到一个更高的墙，本次循环结束
 * 从下一堵墙继续开始
 */
class Solution {
    public int trap(int[] height) {
        int answer = 0;
        Stack<Integer> storedIndex = new Stack<>();
        for (int current = 0; current < height.length; current++) {
            while (!storedIndex.isEmpty() && height[current] > height[storedIndex.peek()]) {
                int top = storedIndex.pop();
                if (storedIndex.isEmpty()) {
                    break;
                }
                int distance = current - storedIndex.peek() - 1;
                int bounded_height = Math.min(height[current], height[storedIndex.peek()]) - height[top];
                answer += bounded_height * distance;
            }
            storedIndex.add(current);
        }
        return answer;
    }
}
