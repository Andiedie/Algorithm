/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 *
 * https://leetcode.com/problems/word-search/description/
 *
 * algorithms
 * Medium (30.27%)
 * Total Accepted:    252.4K
 * Total Submissions: 830.5K
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"'
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * Example:
 * 
 * 
 * board =
 * [
 * ⁠ ['A','B','C','E'],
 * ⁠ ['S','F','C','S'],
 * ⁠ ['A','D','E','E']
 * ]
 * 
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * 
 * 
 */
/**
 * Use solution in https://leetcode.com/problems/word-search/discuss/27658/Accepted-very-short-Java-solution.-No-additional-space
 * 深度优先搜索 + 回溯
 * 使用一个 '#' 来标记访问过的节点，避免重复使用
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (exist(board, x, y, w, 0)) return true;
            }
        }
        return false;
    }
    private boolean exist(char[][] board, int x, int y, char[] word, int i) {
        if (i == word.length) return true;
        if (x < 0 || y < 0 || x == board.length || y == board[x].length) return false;
        if (board[x][y] != word[i]) return false;
        char backup = board[x][y];
        board[x][y] = '#';
        boolean exist = 
            exist(board, x, y + 1, word, i + 1) ||
            exist(board, x, y - 1, word, i + 1) ||
            exist(board, x + 1, y, word, i + 1) ||
            exist(board, x - 1, y, word, i + 1);
        board[x][y] = backup;
        return exist;
    }
}

