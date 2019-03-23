import java.util.Stack;

/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 *
 * https://leetcode.com/problems/min-stack/description/
 *
 * algorithms
 * Easy (35.48%)
 * Total Accepted:    267.7K
 * Total Submissions: 752.9K
 * Testcase Example:  '["MinStack","push","push","push","getMin","pop","top","getMin"]\n[[],[-2],[0],[-3],[],[],[],[]]'
 *
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * 
 * 
 * push(x) -- Push element x onto stack.
 * 
 * 
 * pop() -- Removes the element on top of the stack.
 * 
 * 
 * top() -- Get the top element.
 * 
 * 
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * 
 * 
 */
/**
 * Use solution in https://leetcode.com/problems/min-stack/discuss/49014/Java-accepted-solution-using-one-stack
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
class MinStack {

    /** initialize your data structure here. */
    private int min = Integer.MAX_VALUE;
    private Stack<Integer> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
