package com.caching;

import java.util.concurrent.ConcurrentHashMap;

public class TTLCache {
    private final Long ttl;
    private final ConcurrentHashMap<Integer, Entry> data;

    public TTLCache(long ttl) {
        this.ttl = ttl;
        this.data = new ConcurrentHashMap<>();
    }

    public void put(int key, int value) {
        data.put(key, new Entry(value, System.currentTimeMillis() + ttl));
    }

    public Integer get(int key) {
        Entry entry = data.get(key);
        if (entry == null) return null;
        return entry.value;
    }

    public void cleanup() {
        long currentTime = System.currentTimeMillis();
        data.entrySet().removeIf(entry -> currentTime >= entry.getValue().expiryTime);
    }

    public void printCachedData() {
        data.forEach((key, value) -> System.out.print(key + ":" + value.value + ", "));
        System.out.println();
    }

}

class Entry {
    int value;
    long expiryTime;

    public Entry(int value, long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
    }
}
