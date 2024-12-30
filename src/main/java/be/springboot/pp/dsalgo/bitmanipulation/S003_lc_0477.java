package be.springboot.pp.dsalgo.bitmanipulation;

public class S003_lc_0477 {
    public int totalHammingDistance(int[] nums) {
        int total = 0;
        int n = nums.length;

        // Iterate over each bit position (0 to 31)
        for (int i = 0; i < 32; i++) {
            int countOnes = 0;

            // Count the number of 1s at bit position i
            for (int num : nums)
                if ((num & (1 << i)) != 0) countOnes++;

            // Calculate contribution of this bit position
            int countZeros = n - countOnes;
            total += countOnes * countZeros;
        }

        return total;
    }
}
