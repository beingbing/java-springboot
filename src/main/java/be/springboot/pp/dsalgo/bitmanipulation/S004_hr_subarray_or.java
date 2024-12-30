package be.springboot.pp.dsalgo.bitmanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S004_hr_subarray_or {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        long[] arr = Arrays.stream(br.readLine().trim().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

        bw.write(computeBitwiseORSum(arr, n) + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static long computeBitwiseORSum(long[] arr, int n) {
        long totalSum = 0; // Total sum of contributions
        long mask = 1L << 31; // Start with the most significant bit

        // Iterate over all 32 bits (assuming integers are 32-bit)
        while (mask != 0) {
            int next = n; // Tracks the next index where the bit is set
            // Start from the end of the array, if an element with ith
            // set bit is found, then all subarray starting from that
            // element will have that bit set in bitwise OR.
            for (int j = n - 1; j >= 0; j--) {
                // Check if the current bit is set in arr[j]
                if ((arr[j] & mask) != 0) next = j; // Update 'next' to the current index
                // Add contribution of this bit from subarrays ending at or after 'next'
                totalSum += (n - next) * mask; // 2^j * k; bit value * total subarrays
            }
            mask >>= 1; // Shift mask to the next bit
        }

        return totalSum;
    }
}
