import java.util.HashMap;
import java.util.Map;

/**
 * 在一个字符串 (0<= 字符串长度<=10000，全部由字母组成) 中找到第一个只出现一次的字符, 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 * 
 * 使用 Map 统计每个字符出现的次数
 */

class Solution {
    public int FirstNotRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Integer count = map.get(c);
            if (count == null) count = 0;
            map.put(c, count + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) return i;
        }
        return -1;
    }
}
