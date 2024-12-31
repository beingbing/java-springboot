package be.springboot.pp.dsalgo.rangequeries;

// segment tree technique
public class SegmentTree {
    private final int[] segmentTreeArray; // Stores segment tree nodes
    private final int arrayLength;       // Length of the input array

    public SegmentTree(int[] inputArray) {
        arrayLength = inputArray.length;
        segmentTreeArray = new int[4 * arrayLength]; // Allocate memory for segment tree
        buildTree(inputArray, 0, 0, arrayLength - 1); // Build tree recursively
    }

    // Recursive function to build the segment tree
    private void buildTree(int[] inputArray, int currentNode, int segmentStart, int segmentEnd) {
        // Base case: If segment contains only one element
        if (segmentStart == segmentEnd) segmentTreeArray[currentNode] = inputArray[segmentStart];
        else {
            int midPoint = (segmentStart + segmentEnd) / 2;
            // Recursively build left and right subtrees
            buildTree(inputArray, 2 * currentNode + 1, segmentStart, midPoint);
            buildTree(inputArray, 2 * currentNode + 2, midPoint + 1, segmentEnd);
            // Combine results for parent node
            segmentTreeArray[currentNode] = Math.min(segmentTreeArray[2 * currentNode + 1], segmentTreeArray[2 * currentNode + 2]);
        }
    }

    // Function to handle RMQ query for range [queryLeft, queryRight]
    public int query(int queryLeft, int queryRight) {
        return queryUtil(0, 0, arrayLength - 1, queryLeft, queryRight);
    }

    // Utility function for recursive RMQ query
    private int queryUtil(int currentNode, int segmentStart, int segmentEnd, int queryLeft, int queryRight) {
        // Case 1: Range completely outside the segment
        if (queryRight < segmentStart || queryLeft > segmentEnd) {
            return Integer.MAX_VALUE; // Return max value as it won't affect min operation
        }
        // Case 2: Range completely inside the segment
        if (queryLeft <= segmentStart && queryRight >= segmentEnd) {
            return segmentTreeArray[currentNode];
        }
        // Case 3: Partial overlap, divide further
        int midPoint = (segmentStart + segmentEnd) / 2;
        int leftResult = queryUtil(2 * currentNode + 1, segmentStart, midPoint, queryLeft, queryRight);
        int rightResult = queryUtil(2 * currentNode + 2, midPoint + 1, segmentEnd, queryLeft, queryRight);
        return Math.min(leftResult, rightResult);
    }

    public void update(int updateIndex, int newValue) {
        updateUtil(0, 0, arrayLength - 1, updateIndex, newValue);
    }

    // Utility function to handle updates
    private void updateUtil(int currentNode, int segmentStart, int segmentEnd, int updateIndex, int newValue) {
        if (segmentStart == segmentEnd) {
            segmentTreeArray[currentNode] = newValue;
        } else {
            int midPoint = (segmentStart + segmentEnd) / 2;
            if (updateIndex <= midPoint) {
                updateUtil(2 * currentNode + 1, segmentStart, midPoint, updateIndex, newValue);
            } else {
                updateUtil(2 * currentNode + 2, midPoint + 1, segmentEnd, updateIndex, newValue);
            }
            segmentTreeArray[currentNode] = Math.min(segmentTreeArray[2 * currentNode + 1], segmentTreeArray[2 * currentNode + 2]);
        }
    }
}
