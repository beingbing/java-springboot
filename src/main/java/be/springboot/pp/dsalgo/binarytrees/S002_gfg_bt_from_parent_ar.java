package be.springboot.pp.dsalgo.binarytrees;

public class S002_gfg_bt_from_parent_ar {
    public Node createTree(int[] parent) {
        int n = parent.length;

        Node[] nodes = new Node[n]; // Step 1: Create a TreeNode for each index
        for (int i = 0; i < n; i++) nodes[i] = new Node(i);

        Node root = null;

        for (int i = 0; i < n; i++)
            if (parent[i] == -1) root = nodes[i]; // found the root node
            else {
                Node parentNode = nodes[parent[i]];
                if (parentNode.left == null) parentNode.left = nodes[i];
                else parentNode.right = nodes[i];
            }

        return root;
    }
}
