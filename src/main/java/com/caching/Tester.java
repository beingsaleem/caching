package com.caching;

public class Tester {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);

        lruCache.put(1, 10);
        lruCache.printCachedData();
        lruCache.put(2, 2);
        lruCache.printCachedData();
        lruCache.put(3, 3);
        lruCache.printCachedData();
        lruCache.put(4, 4);
        lruCache.printCachedData();
        lruCache.put(2, 20);
        lruCache.printCachedData();
        lruCache.get(1);
        lruCache.printCachedData();
        lruCache.put(5, 5);
        lruCache.printCachedData();
        lruCache.put(6, 6);
        lruCache.printCachedData();
    }
}
