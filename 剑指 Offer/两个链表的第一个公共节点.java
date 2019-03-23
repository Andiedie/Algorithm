import java.util.HashSet;
import java.util.Set;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 *
 * 如果两个链表有公共节点，那么他们的尾巴一定相同。
 * 三种解法：
 * 1. 计数
 * 第一次循环，记录两个链表的长度
 * 然后让长的链表先向后遍历长度差，直到两个链表的长度相同
 * 此时两个链表一同向后遍历，直到找到共同的节点
 * 时间：O(2(n + m))
 *
 * 2. 无计数器
 * 基本思路和上面的相同，就是先对齐链表的尾部，然后开始遍历
 * 这里使用两个指针指向两个链表的开始位置，然后一同向后遍历
 * 当达到链表尾后，指针从当前链表尾改为指向另一个链表的头
 * 然后继续遍历
 * 这样就相当于形成了两个新的链表，
 * 一个是长链表接短链表，一个是短链表接长链表
 * 两者尾部对齐，所以可以找到
 * 时间：O(2(n + m))
 *
 * 3. Set
 * 遍历第一个链表将元素加入 Set 中
 * 遍历第二个链表逐个查看当前元素是否已经在 Set 中
 * 时间：O(n + m) 空间：O(n)
 */

// class Solution {
//     public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
//         ListNode p1 = pHead1;
//         ListNode p2 = pHead2;
//         while (p1 != p2) {
//             p1 = (p1 == null ? pHead2 : p1.next);
//             p2 = (p2 == null ? pHead1 : p2.next);
//         }
//         return p1;
//     }
// }

class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Set<ListNode> set = new HashSet<>();
        while (pHead1 != null) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (set.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
