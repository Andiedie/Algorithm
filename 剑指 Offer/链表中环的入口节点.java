// import java.util.Set;
// import java.util.HashSet;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出 null。
 * 
 * 三种解法：
 * 一、Set
 * 直接使用 Set 保存所有的遇到的节点
 * 如果某个节点已经在 Set 中出现，则这个节点就是环的入口
 * 时间复杂度 O(N) 空间复杂度 O(N)
 * 
 * 二、切断链表
 * 一前一后两个指针，同时顺着链表向后遍历
 * 后指针每次都将 next 设为 null，即将链表断开
 * 这样当后指针到达 null 的时候，前指针指向的就是链表的入口节点
 * 时间复杂度 O(N) 空间复杂度 O(1)
 * 算法的缺点是，当链表中不存在环时，返回的是最后一个节点
 * 并且会破坏输入的链表
 * 
 * 三、追逐
 * 一快一慢两个指针，同时顺着链表向后遍历，但是快指针的步长为 2，满指针的步长为 1
 * 当两者相遇时，相遇的地点必然在环内（如果不相遇，则表示没有环）
 * 此时将任意一个指针指向链表的头部，然后两者同时用 1 的步长向后遍历
 * 再次相遇的地点就是环的入口
 */

// class Solution {
//     public ListNode EntryNodeOfLoop(ListNode node) {
//         Set<ListNode> set = new HashSet<>();
//         while (node != null) {
//             if (!set.add(node)) return node;
//             node = node.next;
//         }
//         return null;
//     }
// }

// class Solution {
//     public ListNode EntryNodeOfLoop(ListNode node) {
//         if (node == null || node.next == null) return null;
//         ListNode pre = node;
//         ListNode next = pre.next;
//         while (next != null) {
//             pre.next = null;
//             pre = next;
//             next = next.next;
//         }
//         return pre;
//     }
// }

class Solution {
    public ListNode EntryNodeOfLoop(ListNode node) {
        ListNode fast = node, slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }
        if (fast.next == null)
            return null;
        fast = node;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
