package be.springboot.pp.dsalgo.tries;

import java.util.HashMap;
import java.util.Map;

public class S003_lc_0677 {
    public static void main(String[] args) {
        MapSumTrie mapSum = new MapSumTrie();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap")); // Output: 3
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap")); // Output: 5
    }
}

class MapSumTrie {
    private final MapSumTrieNode root;
    private final Map<String, Integer> keyMap;

    public MapSumTrie() {
        root = new MapSumTrieNode();
        keyMap = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - keyMap.getOrDefault(key, 0); // adjust value if key is already present
        keyMap.put(key, val);

        MapSumTrieNode current = root;
        for (char c : key.toCharArray()) {
            current.children.putIfAbsent(c, new MapSumTrieNode());
            current = current.children.get(c);
            current.value += delta; // Update the value for each prefix node
        }
    }

    public int sum(String prefix) {
        MapSumTrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) return 0; // Prefix not found
            current = current.children.get(c);
        }
        return current.value; // Sum of all values under the prefix node
    }
}

class MapSumTrieNode {
    Map<Character, MapSumTrieNode> children;
    int value;

    MapSumTrieNode() {
        children = new HashMap<>();
        value = 0;
    }
}