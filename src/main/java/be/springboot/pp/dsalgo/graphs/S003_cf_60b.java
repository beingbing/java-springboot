package be.springboot.pp.dsalgo.graphs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S003_cf_60b {

    private int dfs(int layer, int row, int col, int depth, int rows, int cols, char[][][] plate, boolean[][][] visited) {
        // Base conditions: Out of bounds or already visited or not water ('.')
        if (layer < 0 || layer >= depth || row < 0 || row >= rows || col < 0 || col >= cols) return 0;
        if (visited[layer][row][col] || plate[layer][row][col] != '.') return 0;

        visited[layer][row][col] = true;

        int count = 1;

        // Explore all 6 possible directions (layer up/down, row up/down, column up/down)
        count += dfs(layer + 1, row, col, depth, rows, cols, plate, visited); // Layer up
        count += dfs(layer - 1, row, col, depth, rows, cols, plate, visited); // Layer down
        count += dfs(layer, row + 1, col, depth, rows, cols, plate, visited); // Row down
        count += dfs(layer, row - 1, col, depth, rows, cols, plate, visited); // Row up
        count += dfs(layer, row, col + 1, depth, rows, cols, plate, visited); // Column right
        count += dfs(layer, row, col - 1, depth, rows, cols, plate, visited); // Column left

        return count;
    }

    private int floodFill(char[][][] plate, int startRow, int startCol, int depth, int rows, int cols) {
        boolean[][][] visited = new boolean[depth][rows][cols]; // Initialize visited array to track visited cells
        return dfs(0, startRow, startCol, depth, rows, cols, plate, visited); // Start DFS from layer 0, startRow, startCol
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        // Step 1: Read dimensions of the 3D grid
        int depth = Integer.parseInt(st.nextToken()); // Number of layers
        int rows = Integer.parseInt(st.nextToken());  // Number of rows in each layer
        int cols = Integer.parseInt(st.nextToken());  // Number of columns in each row
        br.readLine(); // skip empty line

        // Step 2: Initialize the 3D grid and read input values
        char[][][] plate = new char[depth][rows][cols];
        for (int layer = 0; layer < depth; layer++) {
            for (int row = 0; row < rows; row++) plate[layer][row] = br.readLine().trim().toCharArray();
            br.readLine(); // Skip empty line
        }

        // Step 3: Read the starting coordinates for the flood fill
        st = new StringTokenizer(br.readLine().trim());
        int startRow = Integer.parseInt(st.nextToken()) - 1; // Convert to 0-based index
        int startCol = Integer.parseInt(st.nextToken()) - 1; // Convert to 0-based index

        // Step 4: Perform the flood fill operation and output the result
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        S003_cf_60b solver = new S003_cf_60b();
        bw.write(solver.floodFill(plate, startRow, startCol, depth, rows, cols) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
