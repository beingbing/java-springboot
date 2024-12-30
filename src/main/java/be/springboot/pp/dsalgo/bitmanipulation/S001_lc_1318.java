package be.springboot.pp.dsalgo.bitmanipulation;

public class S001_lc_1318 {
    public static int minFlips(int a, int b, int c) {
        int flips = 0; // To store the total flips

        // Iterate through each bit position
        for (int i = 0; i < 32; i++) {
            // Extract the i-th bit of a, b, and c
            int bitA = (a >> i) & 1;
            int bitB = (b >> i) & 1;
            int bitC = (c >> i) & 1;

            if (bitC == 0) {
                // If c's bit is 0, both a and b bits must be 0
                flips += bitA + bitB; // Add 1 flip for each non-zero bit
            } else {
                // If c's bit is 1, at least one of a or b bits must be 1
                if (bitA == 0 && bitB == 0) flips++; // Add 1 flip if both are 0
            }
        }

        return flips;
    }
}
