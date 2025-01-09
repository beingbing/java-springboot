package be.springboot.pp.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class S002_gfg_snl {

    public static int minThrow(int N, int[] arr) {
        // Board array to store the destination for each cell
        int[] board = new int[31];
        for (int i = 1; i <= 30; i++) {
            board[i] = i; // Initialize default move to the same cell
        }

        // Populate board with snakes and ladders
        for (int i = 0; i < 2 * N; i += 2) {
            int start = arr[i];
            int end = arr[i + 1];
            board[start] = end;
        }

        // BFS initialization
        boolean[] visited = new boolean[31];
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(1, 0)); // Start from cell 1 with 0 throws
        visited[1] = true;

        // Perform BFS
        while (!queue.isEmpty()) {
            QueueNode current = queue.poll();
            int currentCell = current.cell;

            // If we reach the last cell, return the number of throws
            if (currentCell == 30) {
                return current.throwsCount;
            }

            // Explore all dice rolls (1 to 6)
            for (int dice = 1; dice <= 6; dice++) {
                int nextCell = currentCell + dice;

                // Ensure next cell is within bounds
                if (nextCell <= 30 && !visited[nextCell]) {
                    visited[nextCell] = true;

                    // Move to the destination cell if there's a snake or ladder
                    int destination = board[nextCell];
                    queue.add(new QueueNode(destination, current.throwsCount + 1));
                }
            }
        }

        // If we exhaust the queue without reaching cell 30, return -1
        return -1;
    }

}

class QueueNode {
    int cell; // Current cell number
    int throwsCount; // Number of dice throws to reach this cell

    QueueNode(int cell, int throwsCount) {
        this.cell = cell;
        this.throwsCount = throwsCount;
    }
}
