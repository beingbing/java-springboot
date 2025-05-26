package be.springboot.pp.dsalgo.dynamicprogramming.module02;

public class S001_gfg_stickler_thief {
    public static int maxSum(int[] a) {
        if (a.length == 0) return 0;
        if (a.length == 1) return a[0];

        int n = a.length;
        int include = a[0]; // Include first element
        int exclude = 0;    // Exclude first element
        System.out.println("include: " + include + " exclude: " + exclude);

        for (int i = 1; i < n; i++) {
            int newInclude = exclude + a[i];  // Include current
            System.out.println("new-include: " + newInclude);
            int newExclude = Math.max(include, exclude); // Exclude current
            System.out.println("new-exclude: " + newExclude);

            include = newInclude;
            exclude = newExclude;
            System.out.println("iterative: include: " + include + " exclude: " + exclude);
        }

        System.out.println("final: include: " + include + " exclude: " + exclude);
        return Math.max(include, exclude);
    }

    public static void main(String[] args) {
        int[] arr = {9, 4, 11, 12, 6, 12};
        System.out.println("Maximum sum of non-adjacent elements: " + maxSum(arr));
    }
}
