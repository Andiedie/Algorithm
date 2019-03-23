/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 
 * 归并
 */

class Solution {
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        while (list1 != null && list2 != null) {
            int val;
            if (list1.val <= list2.val) {
                val = list1.val;
                list1 = list1.next;
            } else {
                val = list2.val;
                list2 = list2.next;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        while (list1 != null) {
            cur.next = new ListNode(list1.val);
            cur = cur.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            cur.next = new ListNode(list2.val);
            cur = cur.next;
            list2 = list2.next;
        }
        return dummyNode.next;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
