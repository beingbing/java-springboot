package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S003_cf_81c {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Input reading
        int n = Integer.parseInt(br.readLine().trim());
        String[] groups = br.readLine().trim().split("\\s+");
        int a = Integer.parseInt(groups[0]);
        int b = Integer.parseInt(groups[1]);

        int[] marks = new int[n];

        String[] elements = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++)  marks[i] = Integer.parseInt(elements[i]);

        // Pair marks with indices
        int[][] markIndexPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            markIndexPairs[i][0] = marks[i]; // Mark value
            markIndexPairs[i][1] = i;       // Original index
        }

        // Sort by mark descending, break ties using indices
        Arrays.sort(markIndexPairs, (x, y) -> {
            if (y[0] == x[0]) {
                if (a < b) return x[1] - y[1]; // assign 1 to smallest indices
                else return y[1] - x[1]; // assign 2 to largest indices
            }
            return y[0] - x[0];
        });

        // Assignment arrays
        int[] result = new int[n];

        if (a != b) {
            int smallerGroup = Math.min(a, b);
            int group1 = a > b ? 2 : 1; // Smaller group gets group 1
            int group2 = a > b ? 1 : 2; // Larger group gets group 2

            // If smaller group is a, assign 1 to bigger numbers
            // If smaller group is b, assign 2 to bigger numbers
            for (int i = 0; i < smallerGroup; i++) result[markIndexPairs[i][1]] = group1;
            for (int i = smallerGroup; i < n; i++) result[markIndexPairs[i][1]] = group2;
        } else {
            for (int i = 0; i < a; i++) result[i] = 1;
            for (int i = a; i < n; i++) result[i] = 2;
        }

        // Output result
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < n; i++) output.append(result[i]).append(" ");
        output.append("\n");

        bw.write(output.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
