package be.springboot.pp.dsalgo.binarytrees;

public class S002_gfg_find_turns_cnt {
    private int turnsFromLCA = 0; // Count turns from LCA to each node

    public int NumberOfTurns(Node root, int first, int second) {
        if (root == null) return -1;

        Node lca = findLCA(root, first, second);
        if (lca == null) return -1;
        turnsFromLCA = 0;

        if (lca.data != first && lca.data != second) turnsFromLCA++; // Increment turn count for diverging paths

        findTurns(lca, first, 0, null);
        findTurns(lca, second, 0, null);

        return turnsFromLCA;
    }

    private Node findLCA(Node root, int first, int second) {
        if (root == null) return null;
        if (root.data == first || root.data == second) return root;

        Node left = findLCA(root.left, first, second);
        Node right = findLCA(root.right, first, second);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    private void findTurns(Node node, int target, int turnsMade, String prevDirection) {
        if (node == null) return;
        if (node.data == target) {
            turnsFromLCA += turnsMade;
            return;
        }

        if (prevDirection == null || prevDirection.equals("left")) findTurns(node.left, target, turnsMade, "left");
        else findTurns(node.left, target, turnsMade + 1, "left"); // Turn occurs

        if (prevDirection == null || prevDirection.equals("right")) findTurns(node.right, target, turnsMade, "right");
        else findTurns(node.right, target, turnsMade + 1, "right"); // Turn occurs
    }
}
