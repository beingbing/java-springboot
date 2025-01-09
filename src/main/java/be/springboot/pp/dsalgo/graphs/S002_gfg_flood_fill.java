package be.springboot.pp.dsalgo.graphs;

public class S002_gfg_flood_fill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc]; // Get the color of the starting pixel
        if (originalColor == newColor) return image; // If the new color is the same as the original color, no need to proceed

        dfs(image, sr, sc, originalColor, newColor); // Start the flood fill process using DFS
        return image;
    }

    private void dfs(int[][] image, int row, int col, int originalColor, int newColor) {
        // Check if the current position is out of bounds or doesn't match the original color
        if (row < 0
                || row >= image.length
                || col < 0
                || col >= image[0].length
                || image[row][col] != originalColor) return;

        image[row][col] = newColor; // Change the color of the current pixel

        dfs(image, row - 1, col, originalColor, newColor); // Up
        dfs(image, row + 1, col, originalColor, newColor); // Down
        dfs(image, row, col - 1, originalColor, newColor); // Left
        dfs(image, row, col + 1, originalColor, newColor); // Right
    }

}
