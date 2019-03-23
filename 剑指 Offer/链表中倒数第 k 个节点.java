/**
 * 输入一个链表，输出该链表中倒数第 k 个结点。
 * 
 * 双指针解法：
 * 先让第一个指针移动 k 次，然后两个指针一起向前移动
 * 当移动到链表尾，返回第二个指针即可
 */

class Solution {
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode first = head;
        ListNode second = head;
        while (k-- > 0) {
            // 如果链表长度不足，返回 null
            if (first == null)
                return null;
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
