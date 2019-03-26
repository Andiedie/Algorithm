import java.util.Stack;

/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
 *
 * https://leetcode.com/problems/binary-search-tree-iterator/description/
 *
 * algorithms
 * Medium (46.87%)
 * Total Accepted:    186.7K
 * Total Submissions: 396.7K
 * Testcase Example:  '["BSTIterator","next","next","hasNext","next","hasNext","next","hasNext","next","hasNext"]\n[[[7,3,15,null,null,9,20]],[null],[null],[null],[null],[null],[null],[null],[null],[null]]'
 *
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 *
 *
 *
 *
 *
 * Example:
 *
 *
 *
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 *
 *
 *
 *
 * Note:
 *
 *
 * next() and hasNext() should run in average O(1) time and uses O(h) memory,
 * where h is the height of the tree.
 * You may assume that next() call will always be valid, that is, there will be
 * at least a next smallest number in the BST when next() is called.
 *
 *
 */
/**
 * 输入一个 BST 的根节点，实现一个 BST 的迭代器
 * 迭代器按照大小顺序遍历 BST
 * next() 和 hasNext() 平均时间复杂度是 O(1), 空间复杂度是 O(h)
 *
 * Use solution in https://leetcode.com/problems/binary-search-tree-iterator/discuss/52526/Ideal-Solution-using-Stack-(Java)
 * 正常情况下，中序遍历一个 BST 就可以从小到大获得所有数
 * 但是这里要求是一个 iterator，并且 next() 和 hasNext() 必须 O(1)
 * 同时空间复杂度是 O(h)，即树高
 *
 * 使用普通的方法：即先中序遍历全树，再将数字从一个一个给出
 * next() 和 hasNext() 虽然 O(1)，但是空间复杂度达到了 O(n)
 * 对于比较大的 BST，这个差别是巨大的
 *
 * 造成空间浪费的主要原因是，我们直接将树摊平成了数组，丢失了结构信息
 * 因此降低空间复杂的突破口，就在于利用树的结构
 *
 * 首先可以确定，最小的值一定在树的最左子节点
 * 我们可以一路遍历，使得一个指针指向子节点
 * 然后呢，在处理完某个节点之后需要回到父节点，但是没有父节点的引用
 * 因此可以使用一个栈在遍历初期就路上的父节点都保存起来
 *
 * 因此我们可以将栈的功能描述为，栈顶的节点永远就是最小值所在的节点
 * 首先沿着树向左遍历，依次加入栈中，此时栈顶的节点一定是最小的
 * 下一个最小值一定在这个节点的右分支上，再在右分支上一路向左遍历，依次加入栈中
 * 如果右节点是空的，那么直接返回父节点即可，它恰好就在栈顶
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode cur = node.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return node.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
