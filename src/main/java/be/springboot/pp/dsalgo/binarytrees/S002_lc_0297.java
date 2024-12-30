package be.springboot.pp.dsalgo.binarytrees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class S002_lc_0297 {
    private static final String NULL_MARKER = "N";
    private static final String DELIMITER = " ";

    public String serialize(Node root) {
        StringBuilder result = new StringBuilder();
        serializeHelper(root, result);
        return result.toString();
    }

    private void serializeHelper(Node root, StringBuilder result) {
        if (root == null) {
            result.append(NULL_MARKER).append(DELIMITER);
            return;
        }
        result.append(root.data).append(DELIMITER);
        serializeHelper(root.left, result);
        serializeHelper(root.right, result);
    }

    public Node deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(nodes);
    }

    private Node deserializeHelper(Queue<String> nodes) {
        String value = nodes.poll();
        if (value.equals(NULL_MARKER)) return null;
        Node node = new Node(Integer.parseInt(value));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        return node;
    }
}
