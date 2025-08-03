package com.caching;

import java.util.LinkedHashMap;
import java.util.Map;

/***
 * Least Recently Used Cache Eviction Strategy
 * Uses Built in linkedHashMap for lru implementation
 */
public class LRUCache2 {
    int capacity;
    LinkedHashMap<Integer, Integer> map;

    public LRUCache2(int capacity){
        this.capacity = capacity;
        map = new LinkedHashMap<>(5, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map .Entry<Integer, Integer> eldest){
                return size() > capacity;
            }
        };
    }

    public int get(int key){
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value){
        map.put(key, value);
    }

    public void printCachedData() {
        System.out.println("Cached data: ");
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            System.out.print(entry.getKey() + ",");
        }
        System.out.println();
    }
}
