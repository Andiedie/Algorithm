import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=679 lang=java
 *
 * [679] 24 Game
 *
 * https://leetcode.com/problems/24-game/description/
 *
 * algorithms
 * Hard (41.44%)
 * Total Accepted:    17.3K
 * Total Submissions: 41.5K
 * Testcase Example:  '[4,1,8,7]'
 *
 * 
 * You have 4 cards each containing a number from 1 to 9.  You need to judge
 * whether they could operated through *, /, +, -, (, ) to get the value of
 * 24.
 * 
 * 
 * Example 1:
 * 
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 2, 1, 2]
 * Output: False
 * 
 * 
 * 
 * Note:
 * 
 * The division operator / represents real division, not integer division.  For
 * example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers.  In particular, we cannot use -
 * as a unary operator.  For example, with [1, 1, 1, 1] as input, the
 * expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together.  For example, if the input is [1,
 * 2, 1, 2], we cannot write this as 12 + 12.
 * 
 * 
 * 
 */
/**
 * Use approach 1 in solution: Backtracking
 * 回溯法
 * 首先需要确认的是，因为输入永远都是四个数，且操作符只有四个，所以最多只有 9216 种情况
 * 1. 从四个数里有顺序地随便选两个数：4 * 3
 * 2. 这两个数通过四种可能的运算后，与剩下的两个数组成三个数的数组：* 4
 * 3. 从三个数里有顺序地随便选两个数：3 * 2
 * 4. 这两个数通过四种可能的运算后，与剩下的一个数组成两个数的数组：* 4
 * 5. 有顺序地取出这两个数：2 * 1
 * 6. 通过四种可能的计算得出结果：* 4
 * 7. 检查结果是否为 24
 * 
 * 一共有 4 * 3 * 4 * 3 * 2 * 4 * 2 * 1 * 4 = 9216 种情况
 * 因此无论输入是什么，最多只需要 9216 次就能确认
 * 时间复杂度 O(1)
 */
class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> numbers = new ArrayList<>();
        for (int v: nums) numbers.add((double) v);
        return solve(numbers);
    }
    private boolean solve(List<Double> nums) {
        if (nums.size() == 0) return false;
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) if (i != j) {
                // 有顺序地取两个数 nums[i] nums[j]
                List<Double> remain = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) if (k != i && k != j) {
                    // 保存剩余的数
                    remain.add(nums.get(k));
                }
                // 对这两个数进行计算
                // k = 
                // 0：加法
                // 1：乘法
                // 2：减法
                // 3：除法
                for (int k = 0; k < 4; k++) {
                    // 对于加法和乘法，由于有交换律的存在，计算一次就可以了
                    // 对于减法和除法，就必须计算 a (op) b 和 b (op) a 两种情况
                    if (k < 2 && j > i) continue;
                    switch (k) {
                        case 0: remain.add(nums.get(i) + nums.get(j)); break;
                        case 1: remain.add(nums.get(i) * nums.get(j)); break;
                        case 2: remain.add(nums.get(i) - nums.get(j)); break;
                        // 且除法要注意除数是否为 0，如果除数为 0，那么这种情况肯定没戏，直接跳过
                        case 3: if (nums.get(j) != 0) {
                            remain.add(nums.get(i) / nums.get(j));
                        } else continue;
                    }
                    if (solve(remain)) return true;
                    remain.remove(remain.size() - 1);
                }
            }
        }
        return false;
    }
}
