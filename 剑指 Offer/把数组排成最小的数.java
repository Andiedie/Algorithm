import java.util.ArrayList;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为 321323。
 * 
 * 假设有两个数 a 和 b，对应的字符串为 A 和 B
 * 将整个数组排序，依据是如果 AB < BA，那么 a 在前，否则 b 在前
 */

class Solution {
    public String PrintMinNumber(int[] numbers) {
        ArrayList<Integer> list = new ArrayList<>(numbers.length);
        for (int i : numbers)
            list.add(i);
        list.sort((a, b) -> {
            String s1 = a.toString() + b.toString();
            String s2 = b.toString() + a.toString();
            return s1.compareTo(s2);
        });
        StringBuilder sb = new StringBuilder();
        for (int i : list)
            sb.append(i);
        return sb.toString();
    }
}
