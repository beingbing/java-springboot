package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class S003_lc_0894 {
    private final Map<Integer, List<Node>> memo = new HashMap<>(); // Memoization map to store results for a given n

    public List<Node> allPossibleFBT(int n) {
        if (n % 2 == 0) return new ArrayList<>();

        if (memo.containsKey(n)) return memo.get(n);

        List<Node> result = new ArrayList<>();

        if (n == 1) {
            result.add(new Node(0));
            memo.put(n, result);
            return result;
        }

        for (int leftCount = 1; leftCount < n; leftCount += 2) { // recursively generates left and right subtrees for each possible split of nodes.
            int rightCount = n - 1 - leftCount;

            List<Node> leftTrees = allPossibleFBT(leftCount);
            List<Node> rightTrees = allPossibleFBT(rightCount);

            // Combine each left tree with each right tree
            for (Node left : leftTrees) {
                for (Node right : rightTrees) {
                    Node root = new Node(0);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        // Memoize and return the result
        memo.put(n, result);
        return result;
    }

    // Utility function to print the tree as a list representation
    public List<List<Integer>> serializeTrees(List<Node> trees) {
        List<List<Integer>> serialized = new ArrayList<>();
        for (Node root : trees) {
            serialized.add(serialize(root));
        }
        return serialized;
    }

    private List<Integer> serialize(Node root) {
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                result.add(null);
            } else {
                result.add(node.data);
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        // Remove trailing nulls for cleaner representation
        while (!result.isEmpty() && result.getLast() == null) {
            result.removeLast();
        }

        return result;
    }
}

class MainPrint {
    public static void main(String[] args) {
        S003_lc_0894 generator = new S003_lc_0894();

        int n = 7;
        List<Node> trees = generator.allPossibleFBT(n);
        List<List<Integer>> serialized = generator.serializeTrees(trees);

        System.out.println("Number of trees: " + trees.size());
        System.out.println("Serialized Trees: " + serialized);
    }
}
