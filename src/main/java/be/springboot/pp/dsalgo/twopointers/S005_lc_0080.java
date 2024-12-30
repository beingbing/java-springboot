package be.springboot.pp.dsalgo.twopointers;

public class S005_lc_0080 {
    public static int removeDuplicates(int[] a) {
        int n = a.length;
        if (n <= 2) return n;

        // Pointer to place the next valid element
        int slow = 2;

        for (int fast = 2; fast < a.length; fast++) {
            // Check if the current element is different from the element at slow-2
            if (a[fast] != a[slow - 2]) {
                a[slow] = a[fast];
                slow++;
            }
        }

        return slow;
    }
}
