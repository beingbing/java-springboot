package be.springboot.pp.dsalgo.twopointers;

public class S000_unique_rects {
    public static int countDistinctRectangles(int[] a, int threshold) {
        int n = a.length, count = 0;
        int left = 0, right = n - 1;

        while (left <= right) {
            int prod = a[left] * a[right];
            if (prod > threshold) right--;
            else {
                count += 2 * (right - left);
                left++;
            }
        }

        return count;
    }
}
