/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *
 * 两种解法：
 * 由于数组内元素的大小为 0 到 n - 1，
 * 也就是说，当数组排序时，所有数字正好和他的下标相同，
 * 可以用坐座位的方式来理解，不重复的情况下，每个数字都有对应的作为
 * 那么我们只需要从头到尾遍历数组，
 * 如果当前数字的坐的座位是错的，就把它移动到正确的座位上
 * 直到某一次，发现当前数字正确的座位上坐的已经是正确的数字，那么当前数字就是重复的
 * 这种解法时间复杂度略高于 O(n)，且会改变原数组
 *
 * 从头到尾遍历数组，每次遇到一个数字，就将这个数字对应下标上的数字 +n 作为标记
 * 如果在做标记前发现这个数字已经被标记了，那么当前数字就是重复数字
 */

// class Solution {
//     public boolean duplicate(int numbers[], int length, int[] duplication) {
//         for (int i = 0; i < length; i++) {
//             while (numbers[i] != i) {
//                 int num = numbers[i];
//                 if (numbers[num] == num) {
//                     duplication[0] = num;
//                     return true;
//                 } else {
//                     swap(numbers, i, num);
//                 }
//             }
//         }
//         return false;
//     }

//     public void swap(int[] a, int i, int j) {
//         int temp = a[i];
//         a[i] = a[j];
//         a[j] = temp;
//     }
// }

class Solution {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (length < 2)
            return false;
        for (int index : numbers) {
            if (index >= length)
                index -= length;
            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }
            numbers[index] += length;
        }
        return false;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
