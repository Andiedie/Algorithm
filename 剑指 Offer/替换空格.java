/**
 * 请实现一个函数，将一个字符串中的每个空格替换成 “%20”。
 * 例如，当字符串为 We Are Happy. 则经过替换之后的字符串为 We%20Are%20Happy。
 * 
 * 如果使用语言自带的特性很容易实现
 * 自己当成 char[] 替换的话，
 * 可以从后往前替换，这样移动数组的次数更少一些
 */
class Solution {
  public String replaceSpace(StringBuffer str) {
      return str.toString().replaceAll(" ", "%20");
  }
}
