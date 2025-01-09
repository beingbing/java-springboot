package be.springboot.pp.dsalgo.graphs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class S004_sp_makemaze {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder results = new StringBuilder();

        int testCases = Integer.parseInt(br.readLine().trim()); // Number of test cases

        while (testCases-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int rows = Integer.parseInt(st.nextToken());
            int cols = Integer.parseInt(st.nextToken());
            char[][] maze = new char[rows][cols];
            for (int i = 0; i < rows; i++) maze[i] = br.readLine().trim().toCharArray(); // Reading the maze
            results.append(isValidMaze(maze, rows, cols) ? "valid" : "invalid").append("\n"); // Validate the maze
        }
        bw.write(results.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isValidMaze(char[][] maze, int rows, int cols) {
        List<Point> openings = new ArrayList<>();

        // Find boundary openings
        for (int i = 0; i < rows; i++) {
            if (maze[i][0] == '.') {
                openings.add(new Point(i, 0)); // Left boundary
                maze[i][0] = '-';
            }
            if (maze[i][cols - 1] == '.') {
                openings.add(new Point(i, cols - 1)); // Right boundary
                maze[i][cols - 1] = '-';
            }
        }
        for (int j = 0; j < cols; j++) {
            if (maze[0][j] == '.') {
                openings.add(new Point(0, j)); // Top boundary
                maze[0][j] = '-';
            }
            if (maze[rows - 1][j] == '.') {
                openings.add(new Point(rows - 1, j)); // Bottom boundary
                maze[rows - 1][j] = '-';
            }
        }

        // If not exactly 2 openings, invalid maze
        if (openings.size() != 2) return false;

        // Check connectivity between the two openings using BFS
        Point start = openings.get(0);
        maze[start.x][start.y] = '.';
        Point end = openings.get(1);
        maze[end.x][end.y] = '.';
        return areConnected(maze, rows, cols, start, end);
    }

    private static boolean areConnected(char[][] maze, int rows, int cols, Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == end.x && current.y == end.y) return true; // If we reach the end point, they are connected

            for (int[] direction : DIRECTIONS) { // Explore 4-connected neighbors
                int newRow = current.x + direction[0];
                int newCol = current.y + direction[1];
                if (newRow >= 0
                        && newRow < rows
                        && newCol >= 0
                        && newCol < cols
                        && maze[newRow][newCol] == '.'
                        && !visited[newRow][newCol]) {
                    queue.add(new Point(newRow, newCol));
                    maze[newRow][newCol] = 'B';
                }
            }
        }

        return false; // If we exhaust the queue without reaching the end, they are not connected
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
