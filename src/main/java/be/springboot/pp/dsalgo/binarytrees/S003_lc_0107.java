package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S003_lc_0107 {
    public List<List<Integer>> levelOrderBottom(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, 0, result);
        Collections.reverse(result);
        return result;
    }

    private void traverse(Node node, int level, List<List<Integer>> result) {
        if (node == null) return;
        if (result.size() <= level) result.add(new ArrayList<>());
        result.get(level).add(node.data);
        traverse(node.left, level + 1, result);
        traverse(node.right, level + 1, result);
    }
}

class S003_lc_0107_iterative {
    public List<List<Integer>> levelOrderBottom(Node root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.data);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(0, level);
        }
        return result;
    }
}
