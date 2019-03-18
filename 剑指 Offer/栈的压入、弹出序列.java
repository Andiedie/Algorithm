import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如序列 1,2,3,4,5 是某栈的压入顺序，
 * 序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列，
 * 但 4,3,5,1,2 就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 * 
 * 使用一个栈模拟这个过程
 * 使用一个指针指示当前需要弹出的元素是什么
 * 只要栈顶元素等于当前需要弹出的元素，就不断弹出
 * 否则不断压入
 */

class Solution {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int indexPop = 0;
        Stack<Integer> s = new Stack<>();
        for (int i : pushA) {
            s.push(i);
            while (!s.isEmpty() && s.peek() == popA[indexPop]) {
                s.pop();
                indexPop++;
            }
        }
        return s.isEmpty();
    }
}