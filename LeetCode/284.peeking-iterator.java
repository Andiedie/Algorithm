import java.util.Arrays;
import java.util.Iterator;

/*
 * @lc app=leetcode id=284 lang=java
 *
 * [284] Peeking Iterator
 *
 * https://leetcode.com/problems/peeking-iterator/description/
 *
 * algorithms
 * Medium (39.23%)
 * Total Accepted:    71.3K
 * Total Submissions: 180.5K
 * Testcase Example:  '["PeekingIterator","next","peek","next","next","hasNext"]\n[[[1,2,3]],[],[],[],[],[]]'
 *
 * Given an Iterator class interface with methods: next() and hasNext(), design
 * and implement a PeekingIterator that support the peek() operation -- it
 * essentially peek() at the element that will be returned by the next call to
 * next().
 *
 * Example:
 *
 *
 * Assume that the iterator is initialized to the beginning of the list:
 * [1,2,3].
 *
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after
 * that still return 2.
 * You call next() the final time and it returns 3, the last element.
 * Calling hasNext() after that should return false.
 *
 *
 * Follow up: How would you extend your design to be generic and work with all
 * types, not just integer?
 *
 */

/**
 * 输入一个迭代器，构造一个新的迭代器，这个迭代器拥有一个新的函数 peek（返回 next 要返回的下一个数）
 */

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> it;
    private Integer next = null;
    private boolean hasNext = false;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
        if (it.hasNext()) {
            next = it.next();
            hasNext = true;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer result = next;
        if (it.hasNext()) {
            next = it.next();
            hasNext = true;
        } else {
            next = null;
            hasNext = false;
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }
}
