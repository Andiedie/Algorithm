/**
 * 求 1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句（A?B:C）。
 * 
 * 可以利用递归求解，但是重要的是不使用 if 等判断跳出条件
 * 因为使用求布尔值短路求值的方法
 */

class Solution {
    public int Sum_Solution(int n) {
        boolean nothing = (n > 0) && ((n += Sum_Solution(n - 1)) > 0);
        return n;
    }
}
