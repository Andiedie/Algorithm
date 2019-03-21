/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * 
 * 四种情况：
 * 1. 当前节点是 null，返回 null
 * 2. 当前节点存在右节点，则返回右节点的最左叶节点
 * 3. 当前节点是父节点的左子节点，则返回父节点
 * 4. 当前节点是父节点的右子节点，则接着向上寻找父节点，重复 3
 */
class Solution {
  public TreeLinkNode GetNext(TreeLinkNode pNode) {
      if (pNode == null) return null;
      if (pNode.right != null) {
          pNode = pNode.right;
          while (pNode.left != null) {
              pNode = pNode.left;
          }
          return pNode;
      }
      while (pNode.next != null) {
          TreeLinkNode parent = pNode.next;
          if (parent.left == pNode) {
              return parent;
          }
          pNode = parent;
      }
      return null;
  }
}

class TreeLinkNode {
  int val;
  TreeLinkNode left = null;
  TreeLinkNode right = null;
  TreeLinkNode next = null;

  TreeLinkNode(int val) {
      this.val = val;
  }
}
