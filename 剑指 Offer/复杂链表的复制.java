/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）
 * 返回结果为复制后复杂链表的 head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 * 
 * 简单的思路是使用 Map
 * 递归复制链表
 * 并将 原节点 -> 新节点 的映射保存下来
 * 处理 Random 时，从 Map 中找到对应的新节点
 * 
 * 或者可以使用“三步法”
 * 1. 将新节点隔位插入到链表中
 * 1 -> 2 -> 3 -> 4
 * 变为
 * 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> 4 -> 4'
 * 
 * 2. 处理 random
 * one = Node
 * clone = one.next
 * clone.random = one.random.next
 * 
 * 3. 拆分链表
 */

// import java.util.HashMap;
// import java.util.Map;

// class Solution {
//     Map<RandomListNode, RandomListNode> map = new HashMap<>();
//     public RandomListNode Clone(RandomListNode pHead) {
//         if (pHead == null) return null;
//         RandomListNode clone = new RandomListNode(pHead.label);
//         map.put(pHead, clone);
//         clone.next = Clone(pHead.next);
//         clone.random = map.get(pHead.random);
//         return clone;
//     }
// }

class Solution {
  public RandomListNode Clone(RandomListNode pHead) {
      if (pHead == null) return null;
      RandomListNode cur = pHead;
      while (cur != null) {
          RandomListNode clone = new RandomListNode(cur.label);
          clone.next = cur.next;
          cur.next = clone;

          cur = clone.next;
      }

      cur = pHead;
      while (cur != null) {
          RandomListNode clone = cur.next;
          if (cur.random != null)
              clone.random = cur.random.next;

          cur = clone.next;
      }

      RandomListNode ans = pHead.next;
      cur = pHead;
      RandomListNode next;
      while (cur.next != null) {
          next = cur.next;
          cur.next = next.next;
          cur = next;
      }
      return ans;
  }
}

class RandomListNode {
  int label;
  RandomListNode next = null;
  RandomListNode random = null;

  RandomListNode(int label) {
      this.label = label;
  }
}
