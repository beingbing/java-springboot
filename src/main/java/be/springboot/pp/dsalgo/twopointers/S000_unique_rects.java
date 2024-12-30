package be.springboot.pp.dsalgo.twopointers;

public class S000_unique_rects {
    public static int countDistinctRectangles(int[] arr, int B) {
        int n = arr.length;
        int i = 0, j = n - 1, count = 0;

        while (i <= j)
            if (arr[i] * arr[j] >= B) j--; // Reduce the larger dimension
            else {
                int subArrayLength = j - i + 1;
                count += 2 * subArrayLength - 1; // -1 to prevent counting i*i twice
                i++; // Move to the next smaller dimension
            }

        return count;
    }
}
