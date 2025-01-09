package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S002_lc_0207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adjList.add(new ArrayList<>());
        int[] inDegree = new int[numCourses];

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int pre = prereq[1];
            adjList.get(pre).add(course); // Add edge pre -> course
            inDegree[course]++;           // Increase in-degree of course
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) queue.offer(i);

        int count = 0; // Count of processed courses
        while (!queue.isEmpty()) {
            int curr = queue.poll(); // Process current node
            count++;

            // Reduce in-degree of neighbors
            for (int neighbor : adjList.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        return count == numCourses; // If not all nodes are processed, there's a cycle
    }
}
