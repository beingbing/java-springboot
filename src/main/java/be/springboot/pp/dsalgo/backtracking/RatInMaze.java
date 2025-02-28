package be.springboot.pp.dsalgo.backtracking;

public class RatInMaze {
    public static void findPaths(int[][] maze, int x, int y, String path, int n) {
        if (x == n - 1 && y == n - 1) { // Destination reached
            System.out.println(path);
            return;
        }

        if (x + 1 < n && maze[x + 1][y] == 0) // Move Down
            findPaths(maze, x + 1, y, path + "D", n);

        if (y + 1 < n && maze[x][y + 1] == 0) // Move Right
            findPaths(maze, x, y + 1, path + "R", n);
    }

    public static void printAllPaths(int[][] maze) {
        int n = maze.length;
        if (maze[0][0] == 1 || maze[n - 1][n - 1] == 1) {
            System.out.println("No valid paths");
            return;
        }
        findPaths(maze, 0, 0, "", n);
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        printAllPaths(maze);
    }
}
