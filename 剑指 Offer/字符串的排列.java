import java.util.ArrayList;
import java.util.Collections;

/**
 * 输入一个字符串, 按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串 abc, 则打印出由字符 a,b,c 所能排列出来的所有字符串 abc,acb,bac,bca,cab 和 cba。
 * 
 * 递归 + 回溯
 * 将第一个元素与每一个元素（包括自己）作交换
 * 然后递归剩余长度
 * 然后交换回来（回溯）
 */

class Solution {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> ans = new ArrayList<>();
        if (str.length() == 0)
            return ans;
        helper(str.toCharArray(), 0, ans);
        // 题目要求必须字典序
        Collections.sort(ans);
        return ans;
    }

    private void helper(char[] str, int start, ArrayList<String> ans) {
        if (start == str.length - 1)
            ans.add(String.valueOf(str));
        for (int i = start; i < str.length; i++) {
            // 排除重复元素
            if (i != start && str[start] == str[i]) {
                continue;
            }
            swap(str, i, start);
            helper(str, start + 1, ans);
            swap(str, i, start);
        }
    }

    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
