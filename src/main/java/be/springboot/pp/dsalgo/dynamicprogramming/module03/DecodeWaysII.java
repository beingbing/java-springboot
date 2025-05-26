package be.springboot.pp.dsalgo.dynamicprogramming.module03;

public class DecodeWaysII {
    private static final int MOD = 1_000_000_007;

    public int numDecodings(String s) {
        int n = s.length();
        long pen = 1, pre = ways(s.charAt(0)), cur;

        for (int i = 1; i < n; i++) {
            cur = (ways(s.charAt(i)) * pre) % MOD;
            cur = (cur + ways(s.charAt(i - 1), s.charAt(i)) * pen) % MOD;

            pen = pre;
            pre = cur;
        }

        return (int) pre;
    }

    private int ways(char c) {
        if (c == '*') return 9;  // '*' can be any of '1'-'9'
        else if (c == '0') return 0;  // '0' can't be decoded alone
        else return 1;  // '1'-'9'
    }

    private int ways(char c1, char c2) {
        if (c1 == '*' && c2 == '*') return 15;  // "11-19" (9) + "21-26" (6)
        if (c1 == '*') return (c2 <= '6') ? 2 : 1;  // "*0-*6" -> 2 ways, "*7-*9" -> 1 way
        if (c2 == '*') {
            if (c1 == '1') return 9;  // "10-19"
            if (c1 == '2') return 6;  // "20-26"
            return 0;
        }
        int num = (c1 - '0') * 10 + (c2 - '0');
        return (num >= 10 && num <= 26) ? 1 : 0;
    }
}