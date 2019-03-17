import java.util.ArrayList;
import java.util.Collections;

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个 ArrayList。
 * 1. 将链表的值存入数组并 reverse
 * 2. 将链表的值存入 Stack
 * 3. 利用递归
 */

class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        while (root != null) {
            ans.add(root.val);
            root = root.next;
        }
        Collections.reverse(ans);
        return ans;
    }
}

class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}
