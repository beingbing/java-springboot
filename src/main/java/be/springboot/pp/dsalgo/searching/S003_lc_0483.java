package be.springboot.pp.dsalgo.searching;

public class S003_lc_0483 {

    public String smallestGoodBase(String str) {
        // Parse the input string to a long integer for numerical calculations
        long n = Long.parseLong(str);

        // We iterate over possible values of m (number of terms - 1 in the series representation)
        // starting from the largest possible m down to 1. The upper bound for m is 60 based on
        // the constraint of n being up to 10^18, giving log_2(10^18) ~ 60
        for (int m = 60; m >= 1; m--) {
            // Set the search range for the base `k`, starting from 2 up to n - 1.
            long low = 2, high = n - 1;

            // Binary search to find the smallest base `k` for the current m value
            while (low <= high) {
                // Calculate the middle base candidate `k` in the current search range
                long k = low + (high - low) / 2;

                // Initialize `sum` for the current series calculation and `currentTerm` to 1 (k^0 term)
                long sum = 0, currentTerm = 1;

                // Flag to detect overflow in the multiplication and summation process
                boolean overflow = false;

                // Calculate the series sum for the base `k` with m terms, i.e., 1 + k + k^2 + ... + k^m
                for (int i = 0; i <= m; i++) {
                    // Add the current term to the series sum
                    sum += currentTerm;

                    // If sum exceeds `n`, no need to continue - break the loop
                    if (sum > n) break;

                    // Before calculating the next term (i.e., `currentTerm *= k`), check for overflow:
                    // Ensure that (n - sum) / currentTerm >= k, otherwise an overflow would occur.
                    if (i < m && (n - sum) / currentTerm < k) {
                        overflow = true;
                        break;
                    }

                    // If safe, move to the next power of `k` (i.e., calculate k^(i+1))
                    if (i < m) currentTerm *= k;
                }

                // If the sum exceeded `n` or overflow was detected, reduce the upper bound (high)
                if (sum > n || overflow) high = k - 1;
                // If the sum is less than `n`, increase the lower bound (low) to try a larger `k`
                else if (sum < n) low = k + 1;
                // If we found a valid base `k` that satisfies the equation, return `k` as a string
                else return Long.toString(k);
            }
        }

        // If no smaller base was found, return "2" as the smallest possible good base by default
        return "1";
    }
}
