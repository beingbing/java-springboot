package be.springboot.pp.dsalgo.narytrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdjacencyListBuilder {

    public static List<List<Integer>> build() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++) adjacencyList.add(new ArrayList<>());

        String[] input;
        for (int i = 0; i < n - 1; i++) {
            input = br.readLine().trim().split("\\s+");
            int startNode = Integer.parseInt(input[0]);
            int endNode = Integer.parseInt(input[1]);
            adjacencyList.get(startNode).add(endNode);
            adjacencyList.get(endNode).add(startNode);
        }

        return adjacencyList;
    }

    public static void printAdjacencyList(List<List<Integer>> adjacencyList) {
        int n = adjacencyList.size();

        for (int i = 1; i <= n; i++) {
            System.out.print(i + ": ");
            for (int child : adjacencyList.get(i)) {
                System.out.print(child + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        AdjacencyListBuilder.printAdjacencyList(AdjacencyListBuilder.build());
    }
}
