package com.caching;

import java.util.HashMap;
import java.util.Map;

/***
 * Least Recently Used cache eviction strategy
 * Uses a hashmap and Doubly linked List
 */
public class LRUCache {
    private final ListNode head;
    private final ListNode tail;
    private final int capacity;
    private final HashMap<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
        map = new HashMap<>();
    }

    private void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    private void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    int get(int key) {
        if (!map.containsKey(key)) return -1;

        ListNode node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    void put(int key, int value) {
        System.out.println("Inserting the node with key: " + key + ", value: "+value);
        if (map.containsKey(key))
            remove(map.get(key));
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        add(node);

        if (map.size() > capacity) {
            ListNode nodeToDelete = this.head.next;
            remove(nodeToDelete);
            map.remove(nodeToDelete.key);
            System.out.println("Deleting the node: " + nodeToDelete.key + ":" + nodeToDelete.value);
        }
    }

    public void printCachedData() {
        System.out.println("Cached data: ");
        for (Map.Entry<Integer, ListNode> entry: map.entrySet()){
            System.out.print(entry.getKey() + ",");
        }
        System.out.println();
    }
}

class ListNode {
    int key, value;
    ListNode prev, next;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/*
 * Complexity Analysis

 * Time complexity: O(1) for both get and put.

 * For get:
 * Check if a key is in a hash map. This costs O(1).
 * Get a node associated with a key. This costs O(1).
 * Call remove and add. Both methods cost O(1).

 * For put:
 * Check if a key is in a hash map. This costs O(1).
 * If it is, we get a node associated with a key and call remove. Both cost O(1).
 * Create a new node and insert it into the hash map. This costs O(1).
 * Call add. This method costs O(1).
 * If the capacity is exceeded, we call remove and delete from the hash map. Both cost O(1).

 * Space complexity: O(capacity)
 * We use extra space for the hash map and for our linked list. Both cannot exceed a size of capacity.
 */
