package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class S001_lc_0093 {

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return result; // Early exit for strings that can't form an IP
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int start, List<String> segments, List<String> result) {
        // If we have exactly 4 segments and used all characters, add to result
        if (segments.size() == 4) {
            if (start == s.length()) result.add(String.join(".", segments));
            return;
        }

        // Try segments of length 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) break;

            String segment = s.substring(start, start + len);
            if (isValidSegment(segment)) {
                segments.add(segment);
                backtrack(s, start + len, segments, result);
                segments.remove(segments.size() - 1); // Backtrack
            }
        }
    }

    // Helper method to validate segment
    private static boolean isValidSegment(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) return false; // No leading zeros
        int value = Integer.parseInt(segment);
        return value >= 0 && value <= 255;
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(restoreIpAddresses(s)); // Expected Output: ["255.255.11.135","255.255.111.35"]
    }
}
