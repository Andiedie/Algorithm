import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 找出所有和为 S 的连续正数序列
 * 
 * 使用一个窗口扫描整数序列
 * 窗口内的和等于 S，则加入答案，窗口左边向右移动
 * 窗口内的和小于 S，则窗口右边向右移动
 * 窗口内的和大于 S，则窗口左边向右移动
 */

class Solution {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int left = 1, right = 2;
        while (left < right) {
            int sum = (left + right) * (right - left + 1) / 2;
            if (sum == target) {
                ArrayList<Integer> a = new ArrayList<>(IntStream.rangeClosed(left, right).boxed().collect(Collectors.toList()));
                ans.add(a);
                left++;
            } else if (sum < target) {
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }
}
