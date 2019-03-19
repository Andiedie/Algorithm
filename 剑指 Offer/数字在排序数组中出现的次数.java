/**
 * 统计一个数字在排序数组中出现的次数。
 * 
 * 用二分分别查找下界和上界
 */
class Solution {
  public int GetNumberOfK(int[] array, int k) {
      int left = lowerBound(array, k);
      if (left == -1) return 0;
      int right = upperBound(array, k);
      return right - left + 1;
  }
  public int lowerBound(int a[], int target) {
      int res = -1;
      int left = 0, right = a.length - 1;
      while (left <= right) {
          int mid = left + (right - left) / 2;
          if (a[mid] == target) {
              res = mid;
              right = mid - 1;
          } else if (a[mid] < target) {
              left = mid + 1;
          } else {
              right = mid - 1;
          }
      }
      return res;
  }

  public int upperBound(int a[], int target) {
      int res = -1;
      int left = 0, right = a.length - 1;
      while (left <= right) {
          int mid = left + (right - left) / 2;
          if (a[mid] == target) {
              res = mid;
              left = mid + 1;
          } else if (a[mid] < target) {
              left = mid + 1;
          } else {
              right = mid - 1;
          }
      }
      return res;
  }
}
