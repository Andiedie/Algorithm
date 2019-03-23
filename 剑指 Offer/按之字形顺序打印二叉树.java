import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 请实现一个函数按照之字形打印二叉树
 * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * 
 * 既然要逐行打印，就可以使用队列（使用链表实现）进行广度优先搜索
 * 使用 null 作为 行分割符
 * 每次遇到 null 就代表换行了
 * 使用一个变量表示当前是否需要逆序，如果需要逆序就使用反向迭代器
 */

class Solution {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (pRoot == null)
            return ans;
        ArrayList<Integer> list = new ArrayList<>();
        // 使用链表实现的队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 分隔符
        queue.addLast(null);
        queue.addLast(pRoot);
        // 是否需要逆序
        boolean reverse = false;
        while (queue.size() > 1) {
            TreeNode node = queue.removeFirst();
            if (node == null) {
                // 遇到了行分隔符
                // 根据是否逆序选择一个迭代器
                // 这里使用迭代器直接迭代 链表，效率比数组逆序要高
                Iterator<TreeNode> iter = reverse ? queue.descendingIterator() : queue.iterator();
                reverse = !reverse;
                while (iter.hasNext()) {
                    list.add(iter.next().val);
                }
                ans.add(list);
                list = new ArrayList<>();
                queue.addLast(null);
                // 注意
                continue;
            }
            // 加入队列中
            if (node.left != null)
                queue.addLast(node.left);
            if (node.right != null)
                queue.addLast(node.right);
        }
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
