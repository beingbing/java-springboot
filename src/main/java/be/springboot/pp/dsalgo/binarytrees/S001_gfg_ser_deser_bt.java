package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.Objects;

public class S001_gfg_ser_deser_bt {
    private final Integer NULL_MARKER = -1;
    private int index = 0;


    private void traverse(Node node, ArrayList<Integer> result) {
        if (node == null) {
            result.add(NULL_MARKER);
            return;
        }
        result.add(node.data);
        traverse(node.left, result);
        traverse(node.right, result);
    }

    public ArrayList<Integer> serialize(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    public Node deSerialize(ArrayList<Integer> a) {
        if (index >= a.size() || Objects.equals(a.get(index), NULL_MARKER)) {
            index++;
            return null;
        }
        Node node = new Node(a.get(index++));
        node.left = deSerialize(a);
        node.right = deSerialize(a);
        return node;
    }
}
