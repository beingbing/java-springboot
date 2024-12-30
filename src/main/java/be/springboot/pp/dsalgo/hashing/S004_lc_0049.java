package be.springboot.pp.dsalgo.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S004_lc_0049 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // Create frequency-based signature
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            // Convert the count array to a string as the key
            StringBuilder keyBuilder = new StringBuilder();
            for (int num : count) {
                keyBuilder.append("#").append(num);
            }
            String key = keyBuilder.toString();

            // Add the string to the group
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
