package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S003_lc_0791 {
    public String customSortString(String order, String s) {
        // Step 1: Create a map to store priorities for characters in 'order'
        Map<Character, Integer> priorityMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            priorityMap.put(order.charAt(i), i);
        }

        // Step 2: Separate characters in 's' based on presence in 'order'
        List<Character> inOrder = new ArrayList<>();
        List<Character> notInOrder = new ArrayList<>();
        for (char c : s.toCharArray())
            if (priorityMap.containsKey(c)) inOrder.add(c);
            else notInOrder.add(c);

        // Step 3: Sort the 'inOrder' list based on custom priority
        inOrder.sort(Comparator.comparingInt(priorityMap::get));

        // Step 4: Combine sorted characters with the rest
        StringBuilder result = new StringBuilder();
        for (char c : inOrder) result.append(c);
        for (char c : notInOrder) result.append(c);

        return result.toString();
    }
}
