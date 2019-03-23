/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 显然，在二叉搜索树中，为了实现有序，必须采用中序遍历
 *
 * 只需要在中序遍历中，使用一个变量 pre 维护上一个节点
 * 然后每次都让当前节点与 pre 建立链表关系，然后更新 pre 为当前节点即可
 *
 * 此时我们拿到的 node 正好位于链表中间
 * 要拿到链表的头部，可以使用一个变量 ans 记录链表头
 * 当第一次执行中序遍历的代码是（此时在最左叶节点），更新一次 ans 即可
 */
class Solution {
    private TreeNode ans = null;
    private TreeNode pre = null;

    public TreeNode Convert(TreeNode node) {
        if (node == null)
            return null;
        Convert(node.left);
        if (pre == null) {
            pre = ans = node;
        } else {
            pre.right = node;
            node.left = pre;
            pre = node;
        }
        Convert(node.right);
        return ans;
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
