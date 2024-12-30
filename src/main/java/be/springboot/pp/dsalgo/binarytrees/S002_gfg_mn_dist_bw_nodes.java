package be.springboot.pp.dsalgo.binarytrees;

public class S002_gfg_mn_dist_bw_nodes {
    private Node findLCA(Node root, Node a, Node b) {
        if (root == null || root == a || root == b) return root;
        Node left = findLCA(root.left, a, b);
        Node right = findLCA(root.right, a, b);
        return (left != null && right != null) ? root : (left != null ? left : right);
    }

    // Function to find the distance of a target node from a given root
    private int findDistance(Node root, Node target, int depth) {
        if (root == null) return -1; // Target not found
        if (root == target) return depth;

        int left = findDistance(root.left, target, depth + 1);
        if (left != -1) return left;
        return findDistance(root.right, target, depth + 1);
    }

    public int findMinDistance(Node root, Node a, Node b) {
        Node lca = findLCA(root, a, b); // Step 1: Find the LCA of the two nodes

        // Step 2: Find the distance from LCA to each node
        int distA = findDistance(lca, a, 0);
        int distB = findDistance(lca, b, 0);

        // Step 3: Combine the distances
        return distA + distB;
    }
}
