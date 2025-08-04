package com.caching;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FIFOCache {
    private final int capacity;
    private final HashMap<Integer, Integer> data;
    private final LinkedList<Integer> order;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.data = new HashMap<>();
        this.order = new LinkedList<>();
    }

    public Integer get(int key) {
        return data.get(key);
    }

    public void put(int key, int value) {
        if (data.containsKey(key)) {
            data.put(key, value);
            return;
        }
        if (data.size() >= capacity) {
            Integer keyToRemove = order.removeFirst();
            data.remove(keyToRemove);
            System.out.println("Cache is full, deleting the key: " + keyToRemove);
        }
        data.put(key, value);
        order.addLast(key);
    }

    public void clear() {
        data.clear();
        order.clear();
    }

    public int getSize() {
        return data.size();
    }

    public void printCachedData(){
//        System.out.print("Cached data: ");
        for(Map.Entry<Integer, Integer> entry: data.entrySet()){
            System.out.print(entry.getKey() + ":" + entry.getValue() + ", ");
        }
        System.out.println();
    }
}
