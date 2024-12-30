package be.springboot.pp.dsalgo.rangequeries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S001_sp_rmqsq {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int q = Integer.parseInt(br.readLine().trim());
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine().trim());
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
        }

        // SqrtDecomposition sqrt = new SqrtDecomposition(arr); // Ideal for static range queries with occasional updates.
        // SparseTable st = new SparseTable(arr); // Best for static range queries when no updates are required.
        SegmentTree segTree = new SegmentTree(arr); // Optimal when frequent updates and queries are needed.

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // Uncomment for the chosen method
            // System.out.println(sqrt.query(l, r));
            // System.out.println(st.query(l, r));
            result.append(segTree.query(l, r)).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}
