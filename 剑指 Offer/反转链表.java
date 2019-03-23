/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 * 
 * 用三个指针
 */

class Solution {
    public ListNode ReverseList(ListNode head) {
        ListNode left = head;
        if (left == null)
            return null;
        ListNode mid = head.next;
        if (mid == null)
            return left;
        ListNode right = mid.next;
        left.next = null;
        mid.next = left;
        while (right != null) {
            ListNode next = right.next;
            right.next = mid;
            left = mid;
            mid = right;
            right = next;
        }
        return mid;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
