/**
 * 汇编语言中有一种移位指令叫做循环左移（ROL）
 * 现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列 S，请你把其循环左移 K 位后的序列输出。
 * 例如，字符序列 S=”abcXYZdef”, 要求输出循环左移 3 位后的结果，即 “XYZdefabc”。
 *
 * 当然可以如下面的注释直接使用子字符串拼接
 *
 * 也可以利用 YX = (X'Y')'
 * 这里 X' 表示 X 的 reverse
 */

// class Solution {
//     public String LeftRotateString(String str, int n) {
//         int len = str.length();
//         if (len == 0)
//             return "";
//         n %= len;
//         return str.substring(n) + str.substring(0, n);
//     }
// }

class Solution {
    public String LeftRotateString(String str, int n) {
        int len = str.length();
        if (len == 0)
            return "";
        n %= len;
        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, len - 1);
        reverse(chars);
        return String.valueOf(chars);
    }

    public void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void reverse(char[] a, int start, int end) {
        int i = start, j = end;
        while (i < j)
            swap(a, i++, j--);
    }

    public void reverse(char[] a) {
        int i = 0, j = a.length - 1;
        while (i < j)
            swap(a, i++, j--);
    }
}
