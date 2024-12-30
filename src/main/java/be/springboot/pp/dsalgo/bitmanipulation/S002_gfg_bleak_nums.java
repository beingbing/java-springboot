package be.springboot.pp.dsalgo.bitmanipulation;

public class S002_gfg_bleak_nums {
    public static String isAccepted(int n) {
        // Calculate the range of x to check: [N - log2(N), N - 1]
        int start = Math.max(0, n - Integer.toBinaryString(n).length() - 1);
        int end = n - 1;

        // Check if any x in the range satisfies x + countSetBits(x) == n
        for (int x = start; x <= end; x++)
            if (x + countSetBits(x) == n) return "0";

        return "1";
    }

    // Function to count set bits in a number using bitwise operations
    public static int countSetBits(int x) {
        int count = 0;
        while (x > 0) {
            count += x & 1; // Increment if the last bit is set
            x >>= 1;        // Right shift to check the next bit
        }
        return count;
    }
}
