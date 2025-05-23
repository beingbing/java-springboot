package be.springboot.pp.dsalgo.maths;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class S002_cf_79b {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());  // rows
        int m = Integer.parseInt(st.nextToken());  // columns
        int k = Integer.parseInt(st.nextToken());  // number of waste cells
        int t = Integer.parseInt(st.nextToken());  // number of queries

        List<Long> waste = new ArrayList<>();

        // Store waste cell positions as 1D indices
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            waste.add(getIndex(a, b, m));
        }

        Collections.sort(waste);

        String[] crops = {"Carrots", "Kiwis", "Grapes"};

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            long index = getIndex(x, y, m);

            // Binary search to check if it's waste
            int wasteIndex = Collections.binarySearch(waste, index);
            if (wasteIndex >= 0)
                sb.append("Waste\n");
            else { // Number of waste cells before this cell
                int insertPoint = -wasteIndex - 1;
                long cultivatedIndex = index - insertPoint;
                sb.append(crops[(int)(cultivatedIndex % 3)]).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // Convert 2D to 1D index
    private static long getIndex(int row, int col, int m) {
        return (long)(row - 1) * m + (col - 1);
    }
}
