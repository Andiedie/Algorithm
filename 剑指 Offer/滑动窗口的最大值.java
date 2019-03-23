import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组 {2,3,4,2,6,2,5,1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口
 * 他们的最大值分别为{4,4,6,6,6,5}； 
 * 针对数组 {2,3,4,2,6,2,5,1} 的滑动窗口有以下 6 个： 
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 
 * 使用一个队列维护当前的最大值列表，
 * 每次加入一个值，就将它队列中所有比他小的值都删掉
 * 每次移动窗口，就将过期的最大值移除
 */

class Solution {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ans = new ArrayList<>(num.length - size + 1);
        if (size == 0)
            return ans;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            while (!list.isEmpty() && num[list.peekLast()] <= num[i]) {
                list.pollLast();
            }
            while (!list.isEmpty() && i - list.peekFirst() + 1 > size) {
                list.pollFirst();
            }
            list.offerLast(i);
            if (i + 1 >= size) {
                ans.add(num[list.peekFirst()]);
            }
        }
        return ans;
    }
}
