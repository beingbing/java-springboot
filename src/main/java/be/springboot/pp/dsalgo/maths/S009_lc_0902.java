package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S009_lc_0902 {

    public static int atMostNGivenDigitSet(String[] digitsStr, int n) {
        int[] digits = Arrays.stream(digitsStr).mapToInt(s -> s.charAt(0) - '0').sorted().toArray();
        String nStr = String.valueOf(n);
        int len = nStr.length();
        int total = 0;
        Integer[][] memo = new Integer[len][2]; // 2 = two possible values for the isLimit boolean flag

        // Part 1: Count all numbers with fewer digits than N
        for (int i = 1; i < len; i++) total += (int)Math.pow(digits.length, i);

        // Part 2: Count numbers with same number of digits as N and <= N
        total += countSameLength(digits, nStr, 0, true, memo);

        return total;
    }

    // pos: current digit position in nStr we are trying to fill
    // isLimit: whether the current digit should be <= the corresponding digit in nStr
    private static int countSameLength(int[] digits, String nStr, int pos, boolean isLimit, Integer[][] memo) {
        if (pos == nStr.length()) return 1; // we have filled all digits

        if (!isLimit && memo[pos][0] != null) return memo[pos][0];
        if (isLimit && memo[pos][1] != null) return memo[pos][1];

        // if isLimit is true, we must stay witin the bounds of N at this digit, otherwise we can go up to 9
        int limit = isLimit ? nStr.charAt(pos) - '0' : 9; // limit for the current digit
        int total = 0;

        for (int d : digits) {
            if (d > limit) break; // all digits are greater than limit
            if (d == 0 && pos == 0 && nStr.length() > 1) continue; // avoid leading 0s

            boolean nextLimit = isLimit && (d == limit);
            total += countSameLength(digits, nStr, pos + 1, nextLimit, memo);
        }

        if (!isLimit) memo[pos][0] = total;
        else memo[pos][1] = total;

        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] a = new String[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) a[i] = st.nextToken();

        System.out.println(atMostNGivenDigitSet(a, m)); // Output: e.g., 78
    }
}
