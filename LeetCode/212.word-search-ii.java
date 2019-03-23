import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 *
 * https://leetcode.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (27.45%)
 * Total Accepted:    99.3K
 * Total Submissions: 360.1K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n["oath","pea","eat","rain"]'
 *
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * Example:
 * 
 * 
 * Input: 
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ⁠ ['o','a','a','n'],
 * ⁠ ['e','t','a','e'],
 * ⁠ ['i','h','k','r'],
 * ⁠ ['i','f','l','v']
 * ]
 * 
 * Output: ["eat","oath"]
 * 
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
/**
 * Use solution in https://leetcode.com/problems/word-search-ii/submissions/
 * 使用深度优先搜索 + 字典树 + 回溯的方法
 * 这题之前先看看 [79] Word Search 和 [208] Implement Trie (Prefix Tree)
 * 首先是关于 Word Search 如何搜索的问题
 * 使用深度优先搜索和回溯，我们在每一个点都尝试能不能搜到目标词
 * 
 * 在这一题中，由于有多个词，朴素的想法是，在每个点都尝试任何一个目标词
 * 
 * 有一个地方可能产生重复计算，那就是相同前缀的词，这个前缀会被搜索多次
 * 因此可以使用字典树加速，这样相同前缀只会被计算一次，当搜索到一个目标词后，继续搜索相同前缀的下一个目标词
 * 
 * 还有一些别的 Java 语法的加速：
 * 1. 使用 String.toCharArray() 而不是 String.charAt()
 * 2. 使用 StringBuilder 而不是 String + String
 * 3. 直接将目标词存在字典树的节点上，而不是使用 StringBuilder
 */
class Solution {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                dfs(board, x, y, root, res);
            }
        }
        return res;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (cur.next[i] == null) {
                    cur.next[i] = new TrieNode();
                }
                cur = cur.next[i];
            }
            cur.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int x, int y, TrieNode node, List<String> ans) {
        if (x < 0 || y < 0 || x == board.length || y == board[x].length)
            return;
        char c = board[x][y];
        int index = c - 'a';
        if (c == '#' || node.next[index] == null)
            return;
        node = node.next[index];
        if (node.word != null) {
            ans.add(node.word);
            // 避免一个词多次出现
            node.word = null;
        }
        board[x][y] = '#';
        dfs(board, x + 1, y, node, ans);
        dfs(board, x - 1, y, node, ans);
        dfs(board, x, y + 1, node, ans);
        dfs(board, x, y - 1, node, ans);
        board[x][y] = c;
    }
}
