import java.util.ArrayList;
import java.util.Arrays;

/**
 * 输入一个递增排序的数组和一个数字 S，在数组中查找两个数，使得他们的和正好是 S，如果有多对数字的和等于 S，输出两个数的乘积最小的。
 *
 * 如果是在无序数组中，可以使用 Set 存储已经遇到的数值
 * 这里的数组是有序的，可以利用这个性质
 * 用两个指针指向数组的头部和尾部
 * 如果此时两个指针指向数值的和为 target，则直接返回
 * 因为头部和尾部两个值差最大，乘积最小
 *
 * 如果和太大，则将右边的指针左移
 * 如果和太小，则将左边的指针右移
 */

class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int sum = array[left] + array[right];
            if (sum == target) {
                return new ArrayList<>(Arrays.asList(array[left], array[right]));
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new ArrayList<>();
    }
}
