package be.springboot.pp.dsalgo.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S007_lc_0229 {

    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0, candidate1 = 0, candidate2 = 0;
        for (int n : nums) {
            if (candidate1 == n) count1++;
            else if (candidate2 == n) count2++;
            else if (count1 == 0) {
                candidate1 = n;
                count1++;
            } else if (count2 == 0) {
                candidate2 = n;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        // verify candidates by counting their actual occurrence
        count1 = 0;
        count2 = 0;
        for (int n : nums) {
            if (n == candidate1) count1++;
            if (n == candidate2) count2++;
        }

        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        if (count1 > n / 3) result.add(candidate1);
        if (count2 > n / 3) result.add(candidate2);
        return result;
    }

    public long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    public long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public long lcm(long a, long b, long c) {
        return lcm(a, lcm(b, c));
    }

    public long findMaxLCM(int N) {
        // If N is small, handle edge cases directly
        if (N <= 2) return N;
        if (N == 3) return 6;

        // Initialize the maximum LCM variable
        long maxLCM = 0;

        // Check the LCM of the following triplets:
        maxLCM = Math.max(maxLCM, lcm(N, N - 1, N - 2));
        maxLCM = Math.max(maxLCM, lcm(N, N - 1, N - 3));
        maxLCM = Math.max(maxLCM, lcm(N, N - 2, N - 3));
        maxLCM = Math.max(maxLCM, lcm(N - 1, N - 2, N - 3));

        // Return the maximum LCM found
        return maxLCM;
    }
}
