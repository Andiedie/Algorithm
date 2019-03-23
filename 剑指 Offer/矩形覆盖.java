/**
 * 如果矩形还差最后一个横着的小矩形就能覆盖完全 则有 f(n - 1) 种情况
 * 如果矩形还差最后两个竖着的小矩形就能覆盖完全，则有 f(n - 2) 种情况
 * f(n) = f(n - 1) + f(n + 1)
 * f(0) = 0;
 * f(1) = 1;
 * f(2) = 2;
 */
class Solution {
    public int RectCover(int target) {
        if (target == 0)
            return 0;
        return f(1, 1, target);
    }

    private int f(int cur, int next, int n) {
        if (n == 0)
            return cur;
        return f(next, cur + next, n - 1);
    }
}
