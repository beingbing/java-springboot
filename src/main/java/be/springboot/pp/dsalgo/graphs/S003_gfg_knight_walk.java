package be.springboot.pp.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class S003_gfg_knight_walk {

    public int minStepToReachTarget(int[] knightPos, int[] targetPos, int N) {
        // Convert to 0-based indexing for easier matrix manipulation
        int startX = knightPos[0] - 1;
        int startY = knightPos[1] - 1;
        int targetX = targetPos[0] - 1;
        int targetY = targetPos[1] - 1;

        // If starting position is the target
        if (startX == targetX && startY == targetY) return 0;

        // Directions a knight can move
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        // Visited matrix to track visited cells
        boolean[][] visited = new boolean[N][N];

        // BFS queue
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(startX, startY, 0));
        visited[startX][startY] = true;

        // BFS traversal
        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            for (int i = 0; i < 8; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                // Check if the new position is valid and not visited
                if (isValid(newX, newY, N) && !visited[newX][newY]) {
                    // If the target is reached, return the steps
                    if (newX == targetX && newY == targetY) {
                        return current.steps + 1;
                    }

                    // Mark as visited and add to the queue
                    visited[newX][newY] = true;
                    queue.add(new Cell(newX, newY, current.steps + 1));
                }
            }
        }

        // If the target cannot be reached
        return -1;
    }

    private static boolean isValid(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) {
        int N = 6;
        int[] knightPos = {4, 5};
        int[] targetPos = {1, 1};
        S003_gfg_knight_walk obj = new S003_gfg_knight_walk();
        System.out.println(obj.minStepToReachTarget(knightPos, targetPos, N)); // Output: 3
    }
}

class Cell {
    int x, y, steps;

    Cell(int x, int y, int steps) {
        this.x = x;
        this.y = y;
        this.steps = steps;
    }
}
