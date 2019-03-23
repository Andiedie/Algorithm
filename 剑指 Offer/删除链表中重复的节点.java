/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点
 * 重复的结点不保留，返回链表头指针。 例如，链表 1->2->3->3->4->4->5 处理后为 1->2->5
 * 
 * 因为在排序的链表中，所以重复节点都是相邻的，直接删除所有相邻的重复节点即可
 */
class Solution {
    public ListNode deleteDuplication(ListNode cur) {
        // 设置一个虚构的头部，比较方便编码
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = cur;
        // 前一个节点
        ListNode pre = dummyHead;
        while (cur != null) {
            // 默认没有重复节点
            boolean duplicated = false;
            // 寻找所有重复的节点
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                duplicated = true;
            }
            // 如果发现了重复节点，就讲前一个节点的 next 设为最后一个重复节点的 next
            // 即跳过了所有的重复节点
            if (duplicated) {
                pre.next = cur.next;
            } else {
                // 否则正常遍历
                pre = cur;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
