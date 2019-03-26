import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * algorithms
 * Easy (44.88%)
 * Total Accepted:    210.9K
 * Total Submissions: 468.5K
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 *
 * Input:
 *
 * ⁠  1
 * ⁠/   \
 * 2     3
 * ⁠\
 * ⁠ 5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * 输入一个二叉树，返回所有根节点到叶子节点的路径
 */

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null)
            return ans;
        helper(root, "", ans);
        return ans;
    }

    private void helper(TreeNode node, String str, List<String> ans) {
        if (node.left != null) {
            helper(node.left, str + node.val + "->", ans);
        }
        if (node.right != null) {
            helper(node.right, str + node.val + "->", ans);
        }
        if (node.left == null && node.right == null) {
            ans.add(str + node.val);
        }
    }
}
