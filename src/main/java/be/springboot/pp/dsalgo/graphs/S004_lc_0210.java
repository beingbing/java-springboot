package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S004_lc_0210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>(); // Step 1: Initialize graph and in-degree array
        for (int i = 0; i < numCourses; i++) adjList.add(new ArrayList<>()); // Adjacency list for each course
        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) { // Step 2: Build graph and compute in-degrees
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adjList.get(pre).add(course); // Add edge pre -> course
            inDegree[course]++;           // Increment in-degree of course
        }

        Queue<Integer> queue = new LinkedList<>(); // Step 3: Add nodes with 0 in-degree to the queue
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) queue.offer(i); // No prerequisites

        // Step 4: Process nodes with 0 in-degree
        int[] result = new int[numCourses];
        int index = 0; // Tracks result array position

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[index++] = node; // Add course to the result

            for (int neighbor : adjList.get(node)) {
                inDegree[neighbor]--; // Reduce in-degree of neighbors
                if (inDegree[neighbor] == 0) queue.offer(neighbor); // Add new 0 in-degree node
            }
        }

        // Step 5: Check for cycles
        if (index != numCourses) return new int[0]; // If not all courses are processed then cycle detected
        return result;
    }
}
