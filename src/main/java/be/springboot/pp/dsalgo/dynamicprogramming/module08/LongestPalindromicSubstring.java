package be.springboot.pp.dsalgo.dynamicprogramming.module08;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int center = 0; center < s.length(); center++) {
            // Odd length palindrome (center at i)
            int len1 = expandFromCenter(s, center, center);
            // Even length palindrome (center between i and i+1)
            int len2 = expandFromCenter(s, center, center + 1);
            int len = Math.max(len1, len2);

            // Update start and end if a longer palindrome is found
            if (len > end - start) {
                // Update the start and end index based on new length
                start = center - (len - 1) / 2;
                end = center + len / 2;
            }
        }

        // Extract the longest palindromic substring
        return s.substring(start, end + 1);
    }

    private int expandFromCenter(String s, int left, int right) {
        // Expand while valid and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // length of palindrome
    }
}
