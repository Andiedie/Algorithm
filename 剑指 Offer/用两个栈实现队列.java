import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。 队列中的元素为 int 类型。
 * 
 * 有三个思路：
 * 1. 入队：无论如何将所有的数压入一号栈
 * 2. 出队：将一号栈的内容倒入二号栈中，弹出二号栈的栈顶，然后再将二号栈的内容倒回一号栈
 * 
 * 1. 入队：如果一号栈是空的，就将二号栈的内容倒回一号栈，压入数据
 * 2. 出队：如果二号栈是空的，就将一号栈的内容倒入二号栈，弹出栈顶
 * 
 * 两者的差别是，前者每次都倒入倒回，后者只有需要的时候才进行这些操作，因此后者在连续弹出时效率更高
 * 
 * 1. 入队：无论如何将所有数压入一号栈
 * 2. 出队：如果二号栈是空的，一号栈的内容倒入二号栈，弹出栈顶
 * 
 * 效率最高
 */

class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
