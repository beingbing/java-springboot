package be.springboot.pp.dsalgo.narytrees;

import java.io.IOException;
import java.util.List;

public class TreeAdjacencyListTraversal {
    private final List<List<Integer>> adjacencyList;

    public TreeAdjacencyListTraversal(List<List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public void traverse(int currentNode, int parentNode) {
        System.out.print(currentNode + " ");

        for (int neighbor : adjacencyList.get(currentNode)) {
            if (neighbor != parentNode) { // Skip the parent node
                traverse(neighbor, currentNode);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> adjacencyList = AdjacencyListBuilder.build();
        TreeAdjacencyListTraversal traverser = new TreeAdjacencyListTraversal(adjacencyList);
        traverser.traverse(0, -1);
    }
}
