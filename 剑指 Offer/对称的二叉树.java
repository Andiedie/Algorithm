/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * 
 * 一个对称的二叉树，其左子树的右子树和右子树的左子树相同
 * 且左子树的左子树和右子树的右子树相同
 * 
 * 递归判断内即可
 */
class Solution {
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;
        return helper(pRoot.left, pRoot.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.val != right.val)
            return false;
        return helper(left.right, right.left) && helper(left.left, right.right);
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
