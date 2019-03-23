/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串 "+100","5e2","-123","3.1416" 和 "-1E-16" 都表示数值。
 * 但是 "12e","1a3.14","1.2.3","+-5" 和 "12e+4.3" 都不是。
 * 
 * 题目要求：
 * 1. 开头可以出现正负号
 * 2. 可以出现 E 或 e 表示科学计数法，但只能出现一次
 * 3. 小数点只能出现一次且 E 后面不能出现小数点
 * 4. E 后可以出现正负号
 * 5. E 的后面不能是空的
 * 6. 此外的其他情况都是非法的
 */
class Solution {
    public boolean isNumeric(char[] str) {
        // 记录 E 是否已经出现
        boolean hasE = false;
        // 记录小数点是否已经出现
        boolean hasDot = false;
        // 当前检查的位置
        int i = 0;
        // 如果开头就是正负号，则直接跳过开头一位
        if (str[0] == '+' || str[0] == '-')
            i++;
        for (; i < str.length; i++) {
            // 数字字符，直接跳过
            if (str[i] >= '0' && str[i] <= '9') {
                continue;
            }
            // E 字符
            if (str[i] == 'e' || str[i] == 'E') {
                // 已经出现，非法
                if (hasE)
                    return false;
                // 标记 E 已经出现了
                hasE = true;
                // E 后面不能出现小数点，所以标记小数点已经出现
                hasDot = true;
                // 如果 E 的后面是正负号则直接跳过
                if ((i + 1) < str.length && str[i + 1] == '+' | str[i + 1] == '-')
                    i++;
                // 如果 E 的后面不能是空的
                if (i + 1 >= str.length)
                    return false;
                continue;
            }
            // 小数点
            if (str[i] == '.') {
                // 已经出现，非法
                if (hasDot)
                    return false;
                // 标记小数点已经出现
                hasDot = true;
                continue;
            }
            // 其他字符都是非法的
            return false;
        }
        return true;
    }
}
