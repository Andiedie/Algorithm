/**
 * 计算 1 到 n 的所有整数中，数字 1 出现的次数
 * 1~13 中包含 1 的数字有 1、10、11、12、13 因此共出现 6 次
 * 
 * 全是数学规律，放弃治疗
 */
class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n <= 0)
            return 0;
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long diviver = i * 10;
            count += (n / diviver) * i + Math.min(Math.max(n % diviver - i + 1, 0), i);
        }
        return count;
    }
}
