import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode id=282 lang=java
 *
 * [282] Expression Add Operators
 *
 * https://leetcode.com/problems/expression-add-operators/description/
 *
 * algorithms
 * Hard (32.16%)
 * Total Accepted:    63.3K
 * Total Submissions: 196.4K
 * Testcase Example:  '"123"\n6'
 *
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 *
 * Example 1:
 *
 *
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 *
 *
 * Example 2:
 *
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 *
 * Example 3:
 *
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 *
 * Example 4:
 *
 *
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 *
 *
 * Example 5:
 *
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 *
 */
/**
 * 给定一个由数字组成的字符串和一个目标数，尝试在字符串中插入 +、-、* 符号，使得字符串形成的数字的计算结果等于目标数
 * 返回所有可能的插入
 *
 * Use approach 1 in solution: Backtracking
 * 回溯法
 * 首先一个最简单的情况，假如任意两个数字是操作数
 * 那么我们只需要向任意两个数字之间插入 + - * 三个操作符中的一个
 * 然后计算整个式子，对比结果即可
 *
 * 但是从 Example 3 可以看出，数字之间可以没有操作符
 * 也就是说，任意数量的数字都可以拼接成一个操作数
 * 我们可以想象有一个操作符是 连接 “~”，作用是直接将前后两个数字连接在一起
 *
 * 下一个问题，如何计算式子的值
 * 当然可以将最后的字符串进行计算，但是这样太耗时间了
 * 在拼接的过程中，就可以通过保留必要上下文的方式，边拼接边计算。
 * 例如：
 * 加法，只需要知道之前计算的值 prev 是多少：prev + num
 * 减法，只需要知道之前计算的值 prev 是多少：prev - num
 * 乘法，需要知道之前计算的值 prev 是多少，还需要知道前一个数字 prevNum 是多少：prev - prevNum + (prevNum * num)
 */
class Solution {
    // 保存最后的结果
    private List<String> answer;
    // 所有输入的数字
    private char[] digits;
    // 目标
    private long target;

    public List<String> addOperators(String num, int target) {
        this.answer = new ArrayList<>();
        if (num.length() == 0)
            return this.answer;
        this.target = target;
        this.digits = num.toCharArray();
        this.recurse(0, 0, 0, 0, new LinkedList<>());
        return this.answer;
    }

    private void recurse(int index, long prevNum, long curNum, long value, LinkedList<String> ops) {
        // 扫描指示：
        // index 代表当前正在扫描的输入的位置
        // 上下文：
        // prevNum 代表前一个操作数的值，计算乘法需要使用
        // curNum 代表操作数的值，任何计算都需要使用
        // value 代表之前累积的值，任何计算都需要使用
        // 暂存结果：
        // ops 暂存当前所有的操作数和操作符

        // 如果扫描结束了
        if (index == this.digits.length) {
            // 如果当前的计算值等于最终结果，且没有待计算的值了
            if (value == this.target && curNum == 0) {
                // 将当前计算式加入结果
                StringBuilder sb = new StringBuilder();
                // 第一个符号永远是 + 号，跳过
                ops.subList(1, ops.size()).forEach(v -> sb.append(v));
                this.answer.add(sb.toString());
            }
            return;
        }
        // 将当前计算的值扩展一个位数
        curNum = curNum * 10 + Character.getNumericValue(this.digits[index]);
        // 当前计算值的字符串表示
        String curNumStr = Long.toString(curNum);

        // 这个判断是为了避免 1 + 05 这样的情况出现
        // 也就是说，只有非 0 前缀才有资格扩展为数
        if (curNum > 0) {
            recurse(index + 1, prevNum, curNum, value, ops);
            // 回溯
            // 虽然没什么要做的就是了
        }

        // 处理加法
        ops.add("+");
        ops.add(curNumStr);
        recurse(index + 1, curNum, 0, value + curNum, ops);
        // 回溯
        ops.removeLast();
        ops.removeLast();

        // 减法和乘法不可能出现在第一位
        if (ops.size() > 0) {
            // 处理减法
            ops.add("-");
            ops.add(curNumStr);
            recurse(index + 1, -curNum, 0, value - curNum, ops);
            // 回溯
            ops.removeLast();
            ops.removeLast();

            // 处理乘法
            ops.add("*");
            ops.add(curNumStr);
            recurse(index + 1, curNum * prevNum, 0, value - prevNum + (curNum * prevNum), ops);
            // 回溯
            ops.removeLast();
            ops.removeLast();
        }
    }
}
