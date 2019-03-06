import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 从左到右找到第一个奇数
 * 现在有两个方向可以选，要么加要么减
 * 如果是减，那么直接将这个奇数位 -1
 * 然后之后的所有数字变为 8
 * 得到一个减法目标
 * 
 * 如果是加，那么直接将这个奇数位 +1
 * 之后的所有数字变为 0
 * 得到加法目标
 * 
 * 然后对比减法目标和加法目标哪个的操作次数（差）更小就行了
 * 
 * 注意的是，在处理加法时，有可能会出现奇数位是 9
 * 此时就需要在前面找一个小于 8 的数字变为偶数
 * 然后后面全部设为 0
 * 例如 86912 -> 88000
 * 如果之前所有数字都大于等于 8 则在最前面补一个 2
 * 例如
 * 88892 -> 200000
 * 91112 -> 200000
 */
class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int cases = 0;
        while (num-- > 0) {
            long test = in.nextLong();
            long count = 0;
            cases++;
            int[] digits = Long.toString(test).chars().map(i -> i - 48).toArray();
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] % 2 == 1) {
                    int[] lower = Arrays.copyOf(digits, digits.length);
                    lower[i]--;
                    for (int j = i + 1; j < lower.length; j++)
                        lower[j] = 8;
                    int[] upper = Arrays.copyOf(digits, digits.length);
                    if (upper[i] == 9) {
                        int index;
                        for (index = i - 1; index >= 0; index--) {
                            if (upper[index] < 8) {
                                break;
                            }
                        }
                        if (index < 0) {
                            int[] temp = new int[upper.length + 1];
                            System.arraycopy(upper, 0, temp, 1, upper.length);
                            upper = temp;
                            index = 0;
                            upper[index] = 0;
                        }
                        upper[index] = upper[index] % 2 == 1 ? upper[index] + 1 : upper[index] + 2;
                        for (int j = index + 1; j < upper.length; j++)
                            upper[j] = 0;
                    } else {
                        upper[i]++;
                        for (int j = i + 1; j < upper.length; j++)
                            upper[j] = 0;
                    }
                    long lowerTimes = test - toLong(lower);
                    long upperTimes = toLong(upper) - test;
                    count = Math.min(lowerTimes, upperTimes);
                    break;
                }
            }
            System.out.println("Case #" + cases + ": " + count);
        }
        in.close();
    }

    private static long toLong(int[] arr) {
        long res = 0;
        for (int i : arr) {
            res = res * 10 + i;
        }
        return res;
    }
}