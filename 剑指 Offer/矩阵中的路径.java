/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 
 * 例如 a b c e s f c s a d e e 这样的 3 X 4 矩阵中包含一条字符串 "bcced" 的路径，但是矩阵中不包含 "abcb" 路径，
 * 因为字符串的第一个字符 b 占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * 
 * 使用深度优先搜索 + 回溯法
 * 访问过的节点设置为 #，回溯时还原原来的值
 */
class Solution {
    private char[] matrix;
    private int cols;
    private char[] str;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        this.matrix = matrix;
        this.cols = cols;
        this.str = str;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (dfs(row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, int index) {
        if (index == str.length)
            return true;
        int cur = row * cols + col;
        if (cur < 0 || cur >= matrix.length)
            return false;
        if (matrix[cur] != str[index])
            return false;
        boolean found = false;
        char saved = matrix[cur];
        matrix[cur] = '#';
        if (!found && dfs(row - 1, col, index + 1))
            found = true;
        if (!found && dfs(row + 1, col, index + 1))
            found = true;
        if (!found && dfs(row, col - 1, index + 1))
            found = true;
        if (!found && dfs(row, col + 1, index + 1))
            found = true;
        matrix[cur] = saved;
        return found;
    }
}
