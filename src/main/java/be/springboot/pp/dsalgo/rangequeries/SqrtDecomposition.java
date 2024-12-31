package be.springboot.pp.dsalgo.rangequeries;

import java.util.Arrays;

// sqrt decomposition technique
public class SqrtDecomposition {
    private final int[] inputArray;         // Original input array
    private final int[] blockMinimumValues; // Precomputed minimum values for each block
    private final int blockSize;            // Size of each block

    // Constructor to initialize and preprocess block minimums
    public SqrtDecomposition(int[] input) {
        int numberOfElements = input.length;
        inputArray = input;

        // Calculate the size of each block as sqrt(n) and determine total blocks needed
        blockSize = (int) Math.sqrt(numberOfElements) + 1;
        blockMinimumValues = new int[blockSize];

        // Initialize block minimum values with maximum integer value
        Arrays.fill(blockMinimumValues, Integer.MAX_VALUE);

        // Precompute minimum values for each block
        for (int index = 0; index < numberOfElements; index++) {
            int blockIndex = index / blockSize; // Determine which block the element belongs to
            blockMinimumValues[blockIndex] = Math.min(blockMinimumValues[blockIndex], inputArray[index]);
        }
    }

    // Function to process RMQ query for the given range [queryLeft, queryRight]
    public int query(int queryLeft, int queryRight) {
        int minimumValue = Integer.MAX_VALUE; // Initialize result with maximum integer value

        // Traverse the partial block from queryLeft to end of its block
        while (queryLeft <= queryRight && queryLeft % blockSize != 0) {
            minimumValue = Math.min(minimumValue, inputArray[queryLeft]);
            queryLeft++;
        }

        // Traverse the complete blocks fully contained within the query range
        while (queryLeft + blockSize - 1 <= queryRight) {
            minimumValue = Math.min(minimumValue, blockMinimumValues[queryLeft / blockSize]);
            queryLeft += blockSize;
        }

        // Traverse the partial block from start of its block to queryRight
        while (queryLeft <= queryRight) {
            minimumValue = Math.min(minimumValue, inputArray[queryLeft]);
            queryLeft++;
        }

        return minimumValue; // Return the minimum value found within the range
    }

    public void update(int updateIndex, int newValue) {
        int blockIndex = updateIndex / blockSize;
        inputArray[updateIndex] = newValue;

        blockMinimumValues[blockIndex] = Integer.MAX_VALUE;
        int start = blockIndex * blockSize;
        int end = Math.min(start + blockSize, inputArray.length);
        for (int i = start; i < end; i++) {
            blockMinimumValues[blockIndex] = Math.min(blockMinimumValues[blockIndex], inputArray[i]);
        }
    }
}
