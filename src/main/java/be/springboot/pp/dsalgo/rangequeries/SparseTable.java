package be.springboot.pp.dsalgo.rangequeries;

// sparse table technique
public class SparseTable {
    private final int[][] sparseTable; // Table to store precomputed results for RMQ
    private final int[] logarithmValues; // Logarithm values for fast lookup

    public SparseTable(int[] inputArray) {
        int numberOfElements = inputArray.length;
        int maxLog = (int) (Math.log(numberOfElements) / Math.log(2)) + 1; // Maximum log value

        // Initialize sparse table and logarithm lookup table
        sparseTable = new int[numberOfElements][maxLog];
        logarithmValues = new int[numberOfElements + 1];

        // Precompute logarithm values for fast lookup
        for (int i = 2; i <= numberOfElements; i++) logarithmValues[i] = logarithmValues[i / 2] + 1;

        // Initialize the sparse table for the base case (interval length = 1)
        for (int index = 0; index < numberOfElements; index++) sparseTable[index][0] = inputArray[index];

        // Fill the sparse table for intervals of size 2^j
        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i + (1 << j) <= numberOfElements; i++) {
                sparseTable[i][j] = Math.min(
                        sparseTable[i][j - 1], // Minimum in the first half of the interval
                        sparseTable[i + (1 << (j - 1))][j - 1] // Minimum in the second half
                );
            }
        }
    }

    public int query(int leftIndex, int rightIndex) {
        // Compute the largest power of 2 that fits in the interval length
        int logValue = logarithmValues[rightIndex - leftIndex + 1];

        // Return the minimum value using precomputed results
        return Math.min(
                sparseTable[leftIndex][logValue], // Minimum for the left interval
                sparseTable[rightIndex - (1 << logValue) + 1][logValue] // Minimum for the right interval
        );
    }

    public void update(int updateIndex, int newValue, int inputArrayLength) {
        int maxLog = (int) (Math.log(inputArrayLength) / Math.log(2)) + 1;

        sparseTable[updateIndex][0] = newValue;
        for (int j = 1; j < maxLog; j++) {
            for (int i = Math.max(0, updateIndex - (1 << (j - 1))); i + (1 << j) <= inputArrayLength; i++) {
                sparseTable[i][j] = Math.min(
                        sparseTable[i][j - 1],
                        sparseTable[i + (1 << (j - 1))][j - 1]
                );
            }
        }
    }
}
