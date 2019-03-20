/**
 * 给定一个一个字符串，由单词和空格组成，请输出将单词顺序翻转之后的字符串
 * 例如 “student. a am I” -> “I am a student.”
 */
class Solution {
  public String ReverseSentence(String str) {
      StringBuilder sb = new StringBuilder();
      String[] words = str.split(" ");
      if (words.length < 1) return str;
      int i;
      for (i = words.length - 1; i > 0; i--) {
          sb.append(words[i]);
          sb.append(" ");
      }
      sb.append(words[i]);
      return sb.toString();
  }
}
