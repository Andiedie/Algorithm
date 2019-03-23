/**
 * 给定一个数组 A[0,1,...,n-1], 请构建一个数组 B[0,1,...,n-1],
 * 其中 B 中的元素 B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * 
 * 如果可以使用除法，那可以先将 A 的乘积算出来，然后在 B[i] 位置除以 A[i]
 * 不可以使用除法的话，可以使用如下方法：
 * B[0] = 1
 * B[1] = B[0] * A[0] = A[0]
 * B[2] = B[1] * A[1] = A[0] * A[1]
 * B[i] = B[i-1] * A[i-1] = A[0] * A[1] * ... * A[i-1]
 * 
 * 设一个数组 C
 * C[n-1] = 1
 * C[n-2] = C[n-1] * A[n-1] = A[n-1]
 * C[n-3] = C[n-2] * A[n-2] = A[n-1] * A[n-2]
 * C[i] = C[i+1] * A[i+1] = A[n-1] * A[n-2] * ... * A[i+1]
 * 
 * 只需要将 B 和 C 的对应位置乘积即可
 * 
 * 由于每一次推算只需要上一次的状态，因此可以使用一个变量节省空间
 */
class Solution {
    public int[] multiply(int[] a) {
        int n = a.length;
        if (n == 0)
            return new int[0];
        int[] b = new int[n];
        int temp = 1;
        for (int i = 0; i < n; temp *= a[i++]) {
            b[i] = temp;
        }
        temp = 1;
        for (int i = n - 1; i >= 0; temp *= a[i--]) {
            b[i] *= temp;
        }
        return b;
    }
}
