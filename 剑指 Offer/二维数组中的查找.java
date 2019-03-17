/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * 同 LeetCode [240] Search a 2D Matrix II
 * 从矩阵的右上角开始搜索
 * 这个位置上，该行的所有元素都比它小，该列的所有元素都比它大
 * 如果目标元素比该元素小，则目标元素一定不在这一列上，可以排除这一列
 * 如果目标元素比该元素大，则目标元素一定不在这一行上，可以排除这一行
 * 直到所有行列都被探索或者该元素正好等于目标，返回结果
 */
class Solution {
  public boolean Find(int target, int [][] array) {
      if (array.length < 1 && array[0].length < 1) return false;
      int row = 0, col = array[0].length - 1;
      while (row < array.length && col >= 0) {
          int cur = array[row][col];
          if (cur == target) return true;
          else if (cur > target) col--;
          else row++;
      }
      return false;
  }
}
