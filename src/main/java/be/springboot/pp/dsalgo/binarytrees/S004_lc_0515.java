package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S004_lc_0515 {
    public List<Integer> largestValues(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(Node node, int level, List<Integer> result) {
        if (node == null) return;
        if (result.size() <= level) result.add(node.data);
        else result.set(level, Math.max(result.get(level), node.data));
        dfs(node.left, level + 1, result);
        dfs(node.right, level + 1, result);
    }
}

class S004_lc_0515_iterative {
    public List<Integer> largestValues(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                max = Math.max(max, node.data);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(max);
        }

        return result;
    }
}
