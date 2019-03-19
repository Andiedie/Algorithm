/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组, 求出这个数组中的逆序对的总数 P。并将 P 对 1000000007 取模的结果输出。即输出 P%1000000007
 * 
 * 归并排序中
 * 归并时从后往前归并，每次如果前半段数组的数字比较大，就加后半段数组的剩余长度
 */
class Solution {
  public int InversePairs(int [] array) {
      int n = array.length;
      if (n < 1) return 0;
      int[] copy = new int[n];
      return merge(array, copy, 0, n - 1) % 1000000007;
  }
  private int merge(int[] arr, int[] copy, int left, int right) {
      if (left >= right) {
          return 0;
      }
      int mid = left + (right - left) / 2;
      int leftValue = merge(arr, copy, left, mid);
      int rightValue = merge(arr, copy, mid + 1, right);

      int count = (leftValue + rightValue) % 1000000007;

      int leftP = mid;
      int rightP = right;
      int copyP = right;
      while (leftP >= left && rightP > mid) {
          if (arr[leftP] > arr[rightP]) {
              copy[copyP--] = arr[leftP--];
              count += rightP - mid;
              count %= 1000000007;
          } else {
              copy[copyP--] = arr[rightP--];
          }
      }
      while (leftP >= left) copy[copyP--] = arr[leftP--];
      while (rightP > mid) copy[copyP--] = arr[rightP--];
      System.arraycopy(copy, left, arr, left, right - left + 1);
      return count % 1000000007;
  }
}
