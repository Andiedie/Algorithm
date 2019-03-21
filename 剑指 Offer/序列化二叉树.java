/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * 
 * 使用前序遍历序列化和反序列化
 */
class Solution {
  private int index = -1;
  String Serialize(TreeNode root) {
      if (root == null) return "#,";
      StringBuilder sb = new StringBuilder();
      sb.append(root.val).append(",");
      sb.append(Serialize(root.left));
      sb.append(Serialize(root.right));
      return sb.toString();
  }
  TreeNode Deserialize(String str) {
     return Deserialize(str.split(","));
  }
  TreeNode Deserialize(String[] vals) {
      index++;
      TreeNode node = null;
      if (index < vals.length && !vals[index].equals("#")) {
          node = new TreeNode(Integer.valueOf(vals[index]));
          node.left = Deserialize(vals);
          node.right = Deserialize(vals);
      }
      return node;
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
