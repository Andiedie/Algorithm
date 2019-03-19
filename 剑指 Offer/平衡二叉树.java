/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 
 * 递归
 * 与计算深度类似，不过在计算时会判断左右深度的合法性
 */
class Solution {
  public boolean IsBalanced_Solution(TreeNode root) {
      return depth(root) != -1;
  }
  private int depth(TreeNode node) {
      if (node == null) return 0;
      int left = depth(node.left);
      if (left == -1) return -1;
      int right = depth(node.right);
      if (right == -1) return -1;
      return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
  }
}

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;

  public TreeNode(int val) {
      this.val = val;

  }

}
