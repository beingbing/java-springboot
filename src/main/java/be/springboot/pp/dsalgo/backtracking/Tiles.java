package be.springboot.pp.dsalgo.backtracking;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : tiles.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        return backtrack(freq);
    }

    private int backtrack(Map<Character, Integer> freq) {
        int count = 0;
        for (char c : freq.keySet()) {
            if (freq.get(c) == 0) continue;
            count++;
            freq.put(c, freq.get(c) - 1);
            count += backtrack(freq);
            freq.put(c, freq.get(c) + 1);
        }
        return count;
    }
}
