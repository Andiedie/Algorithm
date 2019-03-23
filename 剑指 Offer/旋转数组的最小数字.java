import java.util.stream.IntStream;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组 {3,4,5,1,2} 为{1,2,3,4,5}的一个旋转，该数组的最小值为 1。
 * NOTE：给出的所有元素都大于 0，若数组大小为 0，请返回 0。
 *
 * 如果数组中没有重复元素，那么可以使用二分法来解决这个问题：
 * 对于一个非旋转数组，数组的左边一定小于数组的右边
 * 但是在旋转数组中，数组的左边会大于数组的右边
 *
 * 那么只需要维护 left 和 right 两个指针指向数组的左右
 * 每次检查数组的中间 mid 的值与两侧的大小
 * 然后将搜索范围缩小到有旋转数组的一半去
 *
 * 但是在有重复数字的数组中，有可能初选 left、right 和 mid 指向的三个数相同的情况
 * 这是就只能朴素查找一个数组中的最小值
 */

class Solution {
    public int minNumberInRotateArray(int[] arr) {
        if (arr.length < 1)
            return 0;
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while (arr[left] >= arr[right]) {
            if (right - left == 1) {
                mid = right;
                break;
            }
            mid = left + (right - left) / 2;
            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                return IntStream.of(arr).min().getAsInt();
            }
            if (arr[mid] >= arr[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return arr[mid];
    }
}
