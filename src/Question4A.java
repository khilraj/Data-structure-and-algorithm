package src;

import java.util.*;

public class Question4A {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // returns 1
        cache.put(3, 3);
        System.out.println(cache.get(2)); // returns -1
        System.out.println(cache.get(3)); // returns 3
        cache.put(4, 4);
        System.out.println(cache.get(1)); // returns -1
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4

    }


    public static class LFUCache {
        private int capacity;
        private Map<Integer, Integer> cache;
        private Map<Integer, Integer> freq;
        private Map<Integer, Integer> time;
        private int t;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.freq = new HashMap<>();
            this.time = new HashMap<>();
            this.t = 0;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            int value = cache.get(key);
            freq.put(key, freq.get(key) + 1);
            time.put(key, ++t);
            return value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (cache.containsKey(key)) {
                cache.put(key, value);
                freq.put(key, freq.get(key) + 1);
                time.put(key, ++t);
            } else {
                if (cache.size() == capacity) {
                    int minFreq = Collections.min(freq.values());
                    List<Integer> keys = new ArrayList<>();
                    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                        if (entry.getValue() == minFreq) {
                            keys.add(entry.getKey());
                        }
                    }
                    int evictKey = Collections.min(keys, Comparator.comparingInt(k -> time.get(k)));
                    cache.remove(evictKey);
                    freq.remove(evictKey);
                    time.remove(evictKey);
                }
                cache.put(key, value);
                freq.put(key, 1);
                time.put(key, ++t);
            }
        }

    }

}
