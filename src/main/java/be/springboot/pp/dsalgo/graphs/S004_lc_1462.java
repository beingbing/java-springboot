package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

public class S004_lc_1462 {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] reachable = new boolean[numCourses][numCourses]; // Step 1: Initialize adjacency matrix for reachability

        for (int[] pre : prerequisites) // Step 2: Fill the reachable matrix based on direct prerequisites
            reachable[pre[0]][pre[1]] = true; // Mark direct prerequisite

        // Step 3: Floyd-Warshall Algorithm to compute transitive closure
        for (int k = 0; k < numCourses; k++) { // Intermediate nodes
            for (int i = 0; i < numCourses; i++) { // Start node
                for (int j = 0; j < numCourses; j++) { // End node
                    reachable[i][j] = reachable[i][j] || (reachable[i][k] && reachable[k][j]);
                }
            }
        }

        boolean[] result = new boolean[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            result[q] = reachable[u][v]; // Check precomputed reachability
        }

//        return result;
        List<Boolean> answer = new ArrayList<Boolean>(result.length);
        for (boolean i : result) answer.add(i);
        return answer;
    }
}
