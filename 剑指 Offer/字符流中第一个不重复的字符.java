import java.util.LinkedList;
import java.util.Queue;

/**
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g"。
 * 当从该字符流中读出前六个字符 “google"时，第一个只出现一次的字符是"l"。
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 * 
 * 使用一个长度为 int 的数组统计所有字符出现的次数（如果是 Unicode 可以使用 Map）
 * 并使用一个队列维护曾经只出现一次的字符
 * 
 * 读入一个字符，更新计数
 * 如果计数为 1，表示第一次出现，加入队列尾部
 * 
 * 读取最小时，从队列头部取出一个字符
 * 如果该字符的当前计数大于 1，继续读取下一个
 * 直到读取到一个字符当前的计数还是 1
 */

class Solution {
    // Insert one char from stringstream
    private int[] count = new int[128];
    private Queue<Character> queue = new LinkedList<>();
    public void Insert(char ch) {
        count[ch] = Math.min(count[ch] + 1, Integer.MAX_VALUE);
        if (count[ch] == 1) {
            queue.add(ch);
        }
    }

    // return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        while (!queue.isEmpty() && count[queue.peek()] > 1) queue.poll();
        if (queue.isEmpty()) return '#';
        return queue.peek();
    }
}
