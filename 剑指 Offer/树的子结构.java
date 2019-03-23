/**
 * 输入两棵二叉树 A，B，判断 B 是不是 A 的子结构。
 * ps：我们约定空树不是任意一个树的子结构）
 */
class Solution {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        // 如果当前节点相同，则尝试比较子树
        if (root1.val == root2.val) {
            if (isSubtree(root1, root2))
                return true;
        }
        // 否则继续尝试左右子节点
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    // 判断 b 是否是 a 的子树，即 a 是否比 b 有更多的叶节点
    private boolean isSubtree(TreeNode a, TreeNode b) {
        if (b == null)
            return true;
        if (a == null)
            return false;
        if (a.val != b.val)
            return false;
        return isSubtree(a.left, b.left) && isSubtree(a.right, b.right);
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
