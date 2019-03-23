/**
 * 给定一棵二叉搜索树，请找出其中的第 k 小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为 4。
 * 
 * 中序遍历
 */
class Solution {
    private int count = 0;

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null)
            return null;
        TreeNode node = KthNode(pRoot.left, k);
        if (node != null)
            return node;
        if (++count == k)
            return pRoot;
        node = KthNode(pRoot.right, k);
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
