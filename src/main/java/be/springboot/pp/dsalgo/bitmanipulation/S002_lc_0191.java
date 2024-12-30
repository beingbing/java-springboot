package be.springboot.pp.dsalgo.bitmanipulation;

public class S002_lc_0191 {
    public static int countSetBits(int n) {
        int count = 0; // Initialize count of set bits

        // Iterate until n becomes 0
        while (n > 0) {
            n = n & (n - 1); // Remove the rightmost set bit
            count++; // Increment the count for each set bit removed
        }

        return count; // Return the total count of set bits
    }
}
