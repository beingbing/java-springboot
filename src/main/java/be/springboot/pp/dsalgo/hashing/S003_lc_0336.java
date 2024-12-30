package be.springboot.pp.dsalgo.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class S003_lc_0336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length == 0) return result;

        // Step 1: Build the reversed map
        Map<String, Integer> reversedMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) reversedMap.put(new StringBuilder(words[i]).reverse().toString(), i);

        // Step 2: Iterate through each word
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int wordLength = word.length();

            // Check all possible splits
            for (int j = 0; j <= wordLength; j++) {
                String prefix = word.substring(0, j);
                String suffix = word.substring(j);

                // Case 1: If prefix is a palindrome, check for reverse of suffix
                if (isPalindrome(prefix)) {
                    Integer matchIndex = reversedMap.get(suffix);
                    if (matchIndex != null && matchIndex != i) result.add(Arrays.asList(matchIndex, i));
                }

                // Case 2: If suffix is a palindrome, check for reverse of prefix
                // Avoid duplicate checks for the same split when j == 0
                if (j != wordLength && isPalindrome(suffix)) {
                    Integer matchIndex = reversedMap.get(prefix);
                    if (matchIndex != null && matchIndex != i) result.add(Arrays.asList(i, matchIndex));
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) if (str.charAt(left++) != str.charAt(right--)) return false;
        return true;
    }
}
