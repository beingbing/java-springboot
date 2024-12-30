package be.springboot.pp.dsalgo.bitmanipulation;

public class S001_lc_0231 {
    public boolean isPowerOfTwo(int n) {
        // If n is less than or equal to 0, it cannot be a power of two
        if (n <= 0) return false;

        // Use the bitwise AND operation to check if n is a power of two
        return (n & (n - 1)) == 0;
    }
}
