import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列 {1,2,4,7,3,5,6,8} 和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * 
 * 前序遍历，总是先出现节点值，然后紧随子节点的值
 * 根节点值    右子节点
 * ↓       [  ↓   ]
 * 1,2,4,7,3,5,6,8
 *  [  ↑ ]
 *  左子节点
 * 
 * 中序遍历，总是先出现左子节点，然后是节点值，然后是右子节点
 * 左子节点    右子节点
 * [  ↓ ]  [  ↓   ]
 * 4,7,2,1,5,3,8,6
 *       ↑
 *    根节点值
 * 
 * 因此可以取出前序遍历第一个节点的值，它一定是树的根节点
 * 然后在中序遍历中找到这个值的位置，这个值之前的所有数是左子节点，之后的所有数是右子节点
 * 
 * 然后递归下去即可
 * 
 * 可以先将中序遍历重建为 Map，这样可以快速找到值对应的索引
 */

class Solution {
    private int[] pre;
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        this.pre = pre;
        for (int i = 0; i < in.length; i++)
            map.put(in[i], i);
        return helper(0, pre.length - 1, 0, in.length - 1);
    }

    private TreeNode helper(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight)
            return null;
        int rootValue = pre[preLeft];
        int indexInIn = map.get(rootValue);
        TreeNode node = new TreeNode(rootValue);
        node.left = helper(preLeft + 1, preLeft + indexInIn - inLeft, inLeft, indexInIn - 1);
        node.right = helper(preLeft + indexInIn - inLeft + 1, preRight, indexInIn + 1, inRight);
        return node;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
