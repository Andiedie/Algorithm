import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min 函数（时间复杂度应为 O（1））。
 * 同 LeetCode [155] Min Stack
 * 
 * 当然可以直接使用两个栈实现
 * 一个正常使用
 * 另一个栈在更小的值出现时压入，第一个栈弹出时如果弹出的是最小值也一并弹出
 * 
 * 单栈实现方法
 * 使用一个变量 min 维护当前最小值
 * 当一个更小的值出现时，先将当前最小值压入栈中存档
 * 然后压入更小值，并更新 min
 * 
 * 弹出时，如果弹出的值等于当前最小值
 * 那么再弹出一个（就是刚刚的存档），更新 min
 */

class Solution {
    private Stack<Integer> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public void push(int node) {
        if (node < min) {
            stack.push(min);
            min = node;
        }
        stack.push(node);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}