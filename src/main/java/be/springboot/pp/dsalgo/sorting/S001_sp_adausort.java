package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class S001_sp_adausort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of test cases
        int t = sc.nextInt();
        while (t-- > 0) {
            // Input array size
            int n = sc.nextInt();
            int[] arr = new int[n];

            // Read input array
            for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

            List<Pair> pairs = unstableSort(arr, n);

            for (Pair pair : pairs) System.out.print(pair.index + " ");
            System.out.println();
        }

        sc.close();
    }

    private static List<Pair> unstableSort(int[] a, int n) {
        // Step 1: Pair elements with their indices
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) pairs.add(new Pair(a[i], i + 1)); // Indices are 1-based

        // Step 2: Custom sort
        pairs.sort((p1, p2) -> {
            if (p1.value != p2.value) return Integer.compare(p1.value, p2.value); // Ascending by value
            return Integer.compare(p2.index, p1.index); // Descending by index for tie-breaking
        });

        return pairs;
    }
}

class Pair {
    int value, index;

    Pair(int value, int index) {
        this.value = value;
        this.index = index;
    }
}
