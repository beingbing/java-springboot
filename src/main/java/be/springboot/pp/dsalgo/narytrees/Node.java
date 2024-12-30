package be.springboot.pp.dsalgo.narytrees;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int data;
    List<Node> children;

    Node(int val) {
        data = val;
        children = new ArrayList<>();
    }
}
