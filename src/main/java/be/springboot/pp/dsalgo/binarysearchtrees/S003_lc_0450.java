package be.springboot.pp.dsalgo.binarysearchtrees;

public class S003_lc_0450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null; // If the tree is empty or node not found

        // Search for the node to delete
        if (key < root.data) root.left = deleteNode(root.left, key); // Recur to the left subtree
        else if (key > root.data) root.right = deleteNode(root.right, key); // Recur to the right subtree
        else {
            // Node with the key is found

            // Case 1: Node has no children or only one child
            if (root.left == null) return root.right; // Replace with the right child
            else if (root.right == null) return root.left; // Replace with the left child

            // Case 2: Node has two children
            // Find the inorder successor (smallest value in the right subtree)
            TreeNode successor = findMin(root.right);
            root.data = successor.data; // Replace the value of the node to delete with the successor's value
            root.right = deleteNode(root.right, successor.data); // Delete the successor node from the right subtree
        }

        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}

class S003_lc_0450_granular {
    public TreeNode deleteNode(TreeNode root, int key) {
        NodeAndParent nodeAndParent = findNodeAndParent(root, key); // Find the node to delete and its parent
        TreeNode targetNode = nodeAndParent.node();
        TreeNode parentNode = nodeAndParent.parent();

        if (targetNode == null) return root; // Node with the given key does not exist
        if (targetNode == root) return deleteRootNode(root); // Special case: Deleting the root node

        // Handle deletion based on the type of targetNode
        if (isLeafNode(targetNode)) deleteLeafNode(targetNode, parentNode);
        else if (hasSingleChild(targetNode)) deleteSingleChildNode(targetNode, parentNode);
        else deleteDoubleChildNode(targetNode, parentNode);

        return root;
    }

    private NodeAndParent findNodeAndParent(TreeNode root, int key) {
        TreeNode currentNode = root;
        TreeNode parent = null;

        while (currentNode != null) {
            if (currentNode.data == key) break;
            parent = currentNode;
            currentNode = (key < currentNode.data) ? currentNode.left : currentNode.right;
        }

        return new NodeAndParent(currentNode, parent);
    }

    private TreeNode deleteRootNode(TreeNode root) {
        if (isLeafNode(root)) return null;
        if (hasSingleChild(root)) return (root.left != null) ? root.left : root.right;

        // Handle the case where the root has two children
        TreeNode predecessorParent = root;
        TreeNode predecessor = root.left;
        while (predecessor.right != null) {
            predecessorParent = predecessor;
            predecessor = predecessor.right;
        }

        root.data = predecessor.data;

        if (isLeafNode(predecessor)) deleteLeafNode(predecessor, predecessorParent);
        else deleteSingleChildNode(predecessor, predecessorParent);

        return root;
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private void deleteLeafNode(TreeNode node, TreeNode parent) {
        if (parent.left == node) parent.left = null;
        else parent.right = null;
    }

    private boolean hasSingleChild(TreeNode node) {
        return (node.left == null) ^ (node.right == null); // XOR: true if exactly one child exists: ((node->left && !node->right) || (!node->left && node->right));
    }

    private void deleteSingleChildNode(TreeNode node, TreeNode parent) {
        TreeNode child = (node.left != null) ? node.left : node.right;

        if (parent.left == node) parent.left = child;
        else parent.right = child;
    }

    private void deleteDoubleChildNode(TreeNode node, TreeNode parent) {
        // Find the inorder predecessor (maximum value in the left subtree)
        TreeNode predecessorParent = node;
        TreeNode predecessor = node.left;
        while (predecessor.right != null) {
            predecessorParent = predecessor;
            predecessor = predecessor.right;
        }

        // Replace the target node's value with the predecessor's value
        node.data = predecessor.data;

        // Delete the predecessor node
        if (isLeafNode(predecessor)) deleteLeafNode(predecessor, predecessorParent);
        else deleteSingleChildNode(predecessor, predecessorParent);
    }
}

record NodeAndParent(TreeNode node, TreeNode parent) {}
