package be.springboot.pp.dsalgo.hashing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S001_gfg_transf_mat {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder result = new StringBuilder();
        while (T-- > 0) {
            String[] input = br.readLine().trim().split("\\s+");
            int R = Integer.parseInt(input[0]); // Rows
            int C = Integer.parseInt(input[1]); // Columns
            int[][] matrix = new int[R][C];

            // Input matrix
            for (int i = 0; i < R; i++) {
                input = br.readLine().trim().split("\\s+");
                for (int j = 0; j < C; j++) {
                    matrix[i][j] = Integer.parseInt(input[j]);
                }
            }

            // Arrays to track rows and columns to update
            boolean[] rowsToUpdate = new boolean[R];
            boolean[] colsToUpdate = new boolean[C];

            // First pass: Mark rows and columns that need to be updated
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (matrix[i][j] == 1) {
                        rowsToUpdate[i] = true;
                        colsToUpdate[j] = true;
                    }
                }
            }

            // Second pass: Update the matrix
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (rowsToUpdate[i] || colsToUpdate[j]) {
                        matrix[i][j] = 1;
                    }
                }
            }

            // Output the modified matrix
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    result.append(matrix[i][j]).append(" ");
                }
                result.append("\n");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
