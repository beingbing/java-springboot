package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S005_lc_0417 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        for (int col = 0; col < cols; col++) {
            dfs(heights, 0, col, pacificReachable);
            dfs(heights, rows - 1, col, atlanticReachable);
        }
        for (int row = 0; row < rows; row++) {
            dfs(heights, row, 0, pacificReachable);
            dfs(heights, row, cols - 1, atlanticReachable);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if (pacificReachable[row][col] && atlanticReachable[row][col])
                    result.add(Arrays.asList(row, col));

        return result;
    }

    private void dfs(int[][] heights, int row, int col, boolean[][] reachable) {
        reachable[row][col] = true;

        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0
                    && newRow < heights.length
                    && newCol >= 0
                    && newCol < heights[0].length
                    && !reachable[newRow][newCol]
                    && heights[newRow][newCol] >= heights[row][col]) {
                dfs(heights, newRow, newCol, reachable);
            }
        }
    }
}
