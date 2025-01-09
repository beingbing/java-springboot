package be.springboot.pp.dsalgo.graphs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class S001_sp_chunk2 {

    private static List<Integer> generatePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime numbers

        for (int i = 2; i * i <= limit; i++)
            if (isPrime[i])
                for (int j = i * i; j <= limit; j += i) isPrime[j] = false;

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++)
            if (isPrime[i]) primes.add(i);

        return primes;
    }

    // Function to perform DFS and calculate the size of the connected component
    private static int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        int size = 1;

        for (int neighbor : graph.get(node))
            if (!visited[neighbor]) size += dfs(neighbor, graph, visited);

        return size;
    }

    private static int bfsOnDisconnectedGraph(int nodes, List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[nodes + 1]; // 1-based indexing
        int maxSize = 0;

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i] && !adjacencyList.get(i).isEmpty()) {
                int componentSize = dfs(i, adjacencyList, visited);
                maxSize = Math.max(maxSize, componentSize);
            }
        }

        return maxSize;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder results = new StringBuilder();

        List<Integer> primes = generatePrimes(1000000);

        int t = Integer.parseInt(br.readLine().trim()); // Number of test cases
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken()); // Number of girls (nodes)
            int m = Integer.parseInt(st.nextToken()); // Number of connections (edges)

            // Create adjacency list for the graph
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < m; i++) { // Read connections
                st = new StringTokenizer(br.readLine().trim());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int maxSize = bfsOnDisconnectedGraph(n, graph);

            // Output the K-th prime or -1 if no connections
            if (m == 0 || maxSize == 0) results.append("-1").append("\n");
            else results.append(primes.get(maxSize - 1)).append("\n"); // K-th prime
        }

        bw.write(results.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
