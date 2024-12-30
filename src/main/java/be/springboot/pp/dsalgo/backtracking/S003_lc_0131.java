package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class S003_lc_0131 {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    private void backtrack(List<List<String>> result, List<String> currentPartition, String s, int start) {
        // Base case: if we have reached the end of the string, add the current partition to the result
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currentPartition.add(s.substring(start, end + 1));
                backtrack(result, currentPartition, s, end + 1);
                currentPartition.removeLast();
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right)
            if (s.charAt(left++) != s.charAt(right--)) return false;
        return true;
    }

    public static void main(String[] args) {
        S003_lc_0131 partitioner = new S003_lc_0131();
        System.out.println(partitioner.partition("aab"));
        // Expected output: [["a", "a", "b"], ["aa", "b"]]
    }
}
