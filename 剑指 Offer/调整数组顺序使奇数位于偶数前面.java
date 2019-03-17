/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 
 * 使用辅助数组
 * 也可以使用冒泡法
 */

 class Solution {
  public void reOrderArray(int [] arr) {
      int n = arr.length;
      int[] temp = new int[n];
      int indexLeft = 0, indexRight = n - 1;
      for (int left = 0, right = n - 1; left < n; left++, right--) {
          if (arr[left] % 2 == 1) {
              temp[indexLeft++] = arr[left];
          }
          if (arr[right] % 2 == 0) {
              temp[indexRight--] = arr[right];
          }
      }
      System.arraycopy(temp, 0, arr, 0, n);
  }
}
