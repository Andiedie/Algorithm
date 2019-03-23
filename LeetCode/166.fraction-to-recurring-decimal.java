import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=166 lang=java
 *
 * [166] Fraction to Recurring Decimal
 *
 * https://leetcode.com/problems/fraction-to-recurring-decimal/description/
 *
 * algorithms
 * Medium (19.12%)
 * Total Accepted:    82.5K
 * Total Submissions: 431.1K
 * Testcase Example:  '1\n2'
 *
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in
 * parentheses.
 * 
 * Example 1:
 * 
 * 
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * 
 * Example 3:
 * 
 * 
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 * 
 * 
 */
/**
 * Use solution in https://leetcode.com/problems/fraction-to-recurring-decimal/discuss/51106/My-clean-Java-solution
 * 注意要使用 long 防止溢出
 * 使用 Map 记录余数，当余数再次出现时表示循环
 */
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        StringBuilder res = new StringBuilder();
        // 符号位
        res.append((numerator > 0) ^ (denominator > 0) ? "-" : "");

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // 整数部分
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // 小数部分
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            map.put(num, res.length());
        }
        return res.toString();
    }
}
