package be.springboot.pp.dsalgo.sorting;

public class S001_lc_0088 {
    public void merge(int[] a1, int m, int[] a2, int n) {
        // Pointers for a1, a2, and the end of merged array
        int i = m - 1;  // Last valid element in a1
        int j = n - 1;  // Last element in a2
        int k = m + n - 1;  // Last position in a1

        // Merge arrays from the end
        while (i >= 0 && j >= 0) {
            if (a1[i] > a2[j]) {
                a1[k] = a1[i];
                i--;
            } else {
                a1[k] = a2[j];
                j--;
            }
            k--;
        }

        // If a2 has remaining elements, copy them
        while (j >= 0) {
            a1[k] = a2[j];
            j--;
            k--;
        }
        // No need to copy a1 elements as they are already in place
    }
}
