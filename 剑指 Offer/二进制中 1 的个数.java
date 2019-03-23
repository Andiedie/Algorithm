/**
 * 三种方法
 * 见注释
 */

// class Solution {
//     public int NumberOf1(int n) {
//         // 直接使用 Java 的函数
//         return Integer.bitCount(n);
//     }
// }

// class Solution {
//     public int NumberOf1(int n) {
//         // 使用无符号右移
//         // 每次通过 与1 取最低位
//         int count = 0;
//         while (n != 0) {
//             count += n & 1;
//             n >>>= 1;
//         }
//         return count;
//     }
// }

class Solution {
    public int NumberOf1(int n) {
        // 使用 n & (n - 1) 删除最低位的一个 1
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
