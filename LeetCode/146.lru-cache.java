import java.util.HashMap;

/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Hard (23.81%)
 * Total Accepted:    260.3K
 * Total Submissions: 1.1M
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * 
 * 
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 * 
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * 
 * LRUCache cache = new LRUCache( 2 \/* capacity *\/ );
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * 
 */
/**
 * 为了达成 O(1) Get 使用 Map
 * Map 的作用是确认 Key 是否存在，并且快速找到链表节点
 * 
 * 为了达成 O(1) Put 使用双向链表
 * 链表的作用是存键值对，并且列表头上的都是即将淘汰的，列表尾部的都是刚刚被使用的
 * 
 * 首先初始化一下 dummyHead 和 dummpyTail 方便操作
 * Set 流程：
 * 1. 检查 key 是否已经在 Map 中
 * 2. 若不在，则新建一个节点放在链表末尾
 * 3. 若在，将这个节点移动到链表尾部
 * 4. 如果此时存储的容量爆了，将链表头部的节点移除
 * 
 * Get 流程：
 * 1. 检查 Key 是否在 Map 中
 * 2. 若不在，返回 -1
 * 2. 若在，将这个节点移动到末尾，返回相应的值
 */
class LRUCache {
    class Node {
        public int value;
        public int key;
        public Node pre;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> store;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        store = new HashMap<>();
    }

    public int get(int key) {
        Node node = store.get(key);
        if (node == null)
            return -1;
        removeNode(node);
        addNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = store.get(key);
        if (node == null) {
            node = new Node(key, value);
            store.put(key, node);
        } else {
            node.value = value;
            removeNode(node);
        }
        addNode(node);
        if (store.size() > capacity) {
            Node toRemove = head.next;
            removeNode(toRemove);
            store.remove(toRemove.key);
        }
    }

    // 将节点添加到末尾
    private void addNode(Node node) {
        Node pre = tail.pre;
        node.pre = pre;
        pre.next = node;
        node.next = tail;
        tail.pre = node;
    }

    // 将节点从链表删除
    private void removeNode(Node node) {
        Node oldPre = node.pre;
        Node oldNext = node.next;
        oldPre.next = oldNext;
        oldNext.pre = oldPre;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
