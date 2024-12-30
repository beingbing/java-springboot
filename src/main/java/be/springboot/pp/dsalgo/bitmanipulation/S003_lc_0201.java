package be.springboot.pp.dsalgo.bitmanipulation;

public class S003_lc_0201 {
    // Function to compute the bitwise AND of numbers in the range [left, right]
    public static int rangeBitwiseAnd(int left, int right) {
        int shiftCount = 0; // Counter to track the number of shifts

        // Shift both left and right until they are equal
        while (left < right) {
            left >>= 1; // Right shift left by 1
            right >>= 1; // Right shift right by 1
            shiftCount++; // Increment the shift count
        }

        // Shift back the result to its original position
        return left << shiftCount; // Left shift to restore common prefix
    }
}
