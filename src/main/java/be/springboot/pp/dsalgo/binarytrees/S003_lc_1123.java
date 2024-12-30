package be.springboot.pp.dsalgo.binarytrees;

public class S003_lc_1123 {
    public Node lcaDeepestLeaves(Node root) {
        return helper(root).lca;
    }

    private DepthLCAPair helper(Node node) {
        if (node == null) return new DepthLCAPair(0, null); // Base case: null node has depth 0 and no LCA

        DepthLCAPair left = helper(node.left);
        DepthLCAPair right = helper(node.right);

        // Determine the LCA and depth based on the left and right depths
        if (left.depth == right.depth) return new DepthLCAPair(left.depth + 1, node);
        else if (left.depth > right.depth) return new DepthLCAPair(left.depth + 1, left.lca);
        else return new DepthLCAPair(right.depth + 1, right.lca);
    }
}

class DepthLCAPair {
    int depth;
    Node lca;

    DepthLCAPair(int depth, Node lca) {
        this.depth = depth;
        this.lca = lca;
    }
}
