package be.springboot.pp.dsalgo.bitmanipulation;

public class S003_lc_0260 {
    public int[] singleNumber(int[] nums) {
        // Step 1: XOR all numbers to get x ^ y
        int xor = 0;
        for (int num : nums) xor ^= num;

        // Step 2: Find the rightmost set bit in xor
        int diffBit = xor & (-xor);

        // Step 3: Divide numbers into two groups and XOR within each group
        int x = 0, y = 0;
        for (int num : nums) {
            if ((num & diffBit) == 0) x ^= num; // Group where the bit is 0
            else y ^= num; // Group where the bit is 1
        }

        // Step 4: Return the two unique numbers
        return new int[]{x, y};
    }
}
