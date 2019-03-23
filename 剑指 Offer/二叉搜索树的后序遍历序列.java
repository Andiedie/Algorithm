/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。假设输入的数组的任意两个数字都互不相同。
 * 
 * 1 4 3 6 8 7 5
 * 是一个合法的 BST 后序遍历序列
 * 其中，最后一个元素 5 是根元素（根据后序遍历性质）
 * 前面的元素被根节点分为两半，分辨是左子树（1 4 3）和右子树（6 8 7）
 * 判断条件：
 * 1. 左子树上的所有节点都小于根节点，且右子树上的所有节点都大于根节点
 * 2. 左右子树的序列都是合法的
 * 
 * 解法：
 * 1. 取出根节点的值
 * 2. 在序列中从左到右找到第一个比根节点大的值 rightStart
 * 3. 此时 rightStart 左边的序列一定都比根节点小
 * 4. 判断 rightStart 右边的序列是否都比根节点大
 * 5. 如果不是，返回 false
 * 6. 如果是，则递归两个子树
 * 
 * 这里题目认为空序列 [] 应该返回 false，所以做了特殊处理
 * 然而一个空的 BST 是合法的，后序遍历就是空，所以本身应该返回 true 才对
 * 垃圾牛客网
 */

class Solution {
    public boolean VerifySquenceOfBST(int[] s) {
        if (s.length == 0)
            return false;
        return helper(s, 0, s.length - 1);
    }

    private boolean helper(int[] s, int left, int right) {
        if (left >= right)
            return true;
        int root = s[right];
        int rightStart = left;
        while (s[rightStart] < root && rightStart < right)
            rightStart++;
        for (int i = rightStart; i < right; i++) {
            if (s[i] < root)
                return false;
        }
        return helper(s, left, rightStart - 1) && helper(s, rightStart, right - 1);
    }
}
