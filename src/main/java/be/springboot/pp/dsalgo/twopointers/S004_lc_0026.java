package be.springboot.pp.dsalgo.twopointers;

public class S004_lc_0026 {
    public static int removeDuplicates(int[] a) {
        if (a.length == 0) return 0;

        // Pointer to the last seen unique element
        int slow = 0;

        for (int fast = 1; fast < a.length; fast++) {
            // If the current element is different from the last unique element
            if (a[fast] != a[slow]) {
                slow++; // Move the unique pointer
                a[slow] = a[fast]; // Overwrite with the new unique value
            }
        }

        // returning size of array containing unique elements.
        return slow + 1; // (1-indexed)
    }
}
