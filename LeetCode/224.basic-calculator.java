import java.util.Stack;

/*
 * @lc app=leetcode id=224 lang=java
 *
 * [224] Basic Calculator
 *
 * https://leetcode.com/problems/basic-calculator/description/
 *
 * algorithms
 * Hard (31.63%)
 * Total Accepted:    96K
 * Total Submissions: 302.4K
 * Testcase Example:  '"1 + 1"'
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces  .
 *
 * Example 1:
 *
 *
 * Input: "1 + 1"
 * Output: 2
 *
 *
 * Example 2:
 *
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 *
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 *
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 *
 *
 */
/**
 * 实现一个简单的计算函数，输入包括加号 +、减号 -、非负整数和空格。
 *
 * Use solution in https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack
 * 由于存在括号，且括号之前有负号的话则会对计算结果造成影响
 * 因此用个 Stack 保存进入括号之前的上下文
 * 退出括号之后合并上下文
 */
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] input = s.toCharArray();
        int sign = 1;
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            switch (c) {
            case '+':
                sign = 1;
                break;
            case '-':
                sign = -1;
                break;
            case '(':
                stack.add(result);
                stack.add(sign);
                result = 0;
                sign = 1;
                break;
            case ')':
                int prevSign = stack.pop();
                int prevResult = stack.pop();
                result = prevResult + prevSign * result;
            default:
                if (!Character.isDigit(c))
                    break;
                int start = i, end = i + 1;
                while (end < input.length && Character.isDigit(input[end])) {
                    end++;
                    i++;
                }
                result += sign * Integer.parseInt(s.substring(start, end));
                sign = 1;
            }
        }
        return result;
    }
}
