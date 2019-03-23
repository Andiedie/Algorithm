/**
 * 地上有一个 m 行和 n 列的方格。
 * 一个机器人从坐标 0,0 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 k 的格子。 
 * 例如，当 k 为 18 时，机器人能够进入方格（35,37），因为 3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为 3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * 
 * 深度优先搜索
 */
class Solution {
    private int rows;
    private int cols;
    private int threshold;
    private int count = 0;
    boolean[][] visited;

    public int movingCount(int threshold, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        this.visited = new boolean[rows][cols];
        dfs(0, 0);
        return count;
    }

    private void dfs(int row, int col) {
        if (row < 0 || row >= rows)
            return;
        if (col < 0 || col >= cols)
            return;
        if (visited[row][col])
            return;
        if (cal(row) + cal(col) > threshold)
            return;
        count++;
        visited[row][col] = true;
        dfs(row - 1, col);
        dfs(row + 1, col);
        dfs(row, col - 1);
        dfs(row, col + 1);
    }

    private int cal(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}
