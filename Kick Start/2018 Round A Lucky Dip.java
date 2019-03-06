import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 动态规划
 * 
 * 首先使用 dp[k] 表示可以重来 k 次时的期望
 * 显然，dp[0] = sum(v) / n
 * 那么 dp[1] 如何计算呢？
 * 由于每一次取物品是完全独立的事件
 * 为了保证最大利益，我们会在第二次取物品时，进行判断
 * 如果第二次取的物品的价值，比上一次取物品的价值数学期望要高，就值得留下这个物品
 * 因此 dp[1] = sum(max(vi, dp[0])) / n
 * 类推：
 * dp[k] = sum(max(vi, dp[k - 1])) / n
 * 这样的时间复杂度是 O(NK)，即每次重取都需要遍历数组
 * 
 * 优化空间复杂度：
 * 由于每一次都只需要前一次的状态，所以可以优化空间复杂度为 O(1)
 * dp = sum(max(vi, dp)) / n
 * 
 * 优化时间复杂度：
 * 首先
 * 最大期望价值 = 拿物品的最大价值总和 / 数量
 * 
 * 数量是知道的，因此只需要计算这一次拿东西时，最大价值总和是多少
 * 上面的方法，我们是通过将每个物品都和上次比较，来确认拿这个物品能不能提高最大价值总和
 * 
 * 逐个比较太费时间，观察 max(vi, dp[k - 1])
 * 当 vi < dp[k - 1] 时，都是选择 dp[k - 1]
 * 那么假如我们将价值数组 v 升序排序，通过二分查找就可以快速将物品分成两个部分
 * 比上一次期望价值要低的物品 和 比上一次期望价值要高的物品
 * 
 * 对于第一个部分，显然放回去（即选择上一次的期望价值）要更好
 * 对于第二部分，显然选择这个物品会更好（提高了最大价值总和）
 * 
 * 假设比上一次期望价值要低的物品数量是 q
 * 那么第一部分的总价值 q * dp[k - 1]
 * 第二部分的总价值就等于 sum(V[i>q])
 * 那么最大总价值就是 q * dp[k - 1] + sum(V[i>q])
 * 最大期望价值就是 {q * dp[k - 1] + sum(V[i>q])} / n
 * 
 * 到这一步可以有两个时间复杂度提速：
 * 1. 使用二分搜索
 * 2. 提前计算好 v 数组的后缀和
 * 
 * 总时间复杂度为 O((N + K)logN)
 */
class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 1; i <= times; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            long[] v = new long[n];
            for (int j = 0; j < n; j++) v[j] = in.nextLong();
            Arrays.sort(v);
            double[] suffixV = new double[v.length + 1];
            suffixV[v.length] = 0;
            for (int j = v.length - 1; j >= 0; j--) {
                suffixV[j] = suffixV[j + 1] + v[j];
            }
            double dp = suffixV[0] / n;
            while (k-- > 0) {
                int quantity = binarySearch(v, dp) + 1;
                dp = (quantity * dp) / n + suffixV[quantity] / n;
            }
            System.out.println(String.format("Case #%d: %f", i, dp));
        }
        in.close();
    }
    private static int binarySearch(long[] arr, double target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (Math.abs(arr[mid] - target) < 1e-6) return mid;
            if (arr[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return hi;
    }
}
