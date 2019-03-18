import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * 
 * 使用一个 LinkedList 维护路径
 * 然后进行深度优先搜索
 * 一旦达到一个叶节点，且 target = 0，则添加一个答案
 * 然后回溯
 */

class Solution {
    private ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return ans;
        path.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            ans.add(new ArrayList<Integer>(path));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        path.removeLast();
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
