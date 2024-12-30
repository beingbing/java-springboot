package be.springboot.pp.dsalgo.narytrees;

import java.util.ArrayList;
import java.util.Stack;

public class TreePreorderTraversal {
    private ArrayList<Integer> preorder;

    public ArrayList<Integer> traverse(Node root) {
        preorder = new ArrayList<>();
        iteration(root);
        recursion(root);
        return preorder;
    }

    private void iteration(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node temp = stack.peek();
            stack.pop();
            preorder.add(temp.data);
            for (int i = temp.children.size() - 1; i >= 0; i--) stack.push(temp.children.get(i));
        }
    }

    private void recursion(Node root) {
        if (root == null) return;
        preorder.add(root.data);
        for (int i = 0; i < root.children.size(); i++) recursion(root.children.get(i));
    }

    public static void main(String[] args) {
        TreePreorderTraversal traverser = new TreePreorderTraversal();
        ArrayList<Integer> list = traverser.traverse(null);

        for (Integer i : list) System.out.print(i + " ");
        System.out.println();
    }
}
