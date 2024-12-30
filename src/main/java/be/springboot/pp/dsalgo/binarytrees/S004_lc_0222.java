package be.springboot.pp.dsalgo.binarytrees;

public class S004_lc_0222 {
    public int countNodes(Node root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight == rightHeight) return (1 << leftHeight) + countNodes(root.right); // LST is perfect, RST is complete
        else return (1 << rightHeight) + countNodes(root.left); // RST is perfect, LST is complete
    }

    private int getHeight(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left; // Go down the leftmost path
        }
        return height;
    }
}

class S004_lc_0222_iterative {
    public int countNodes(Node root) {
        if (root == null) return 0;

        int count = 0;
        Node node = root;

        while (node != null) {
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);

            if (leftHeight == rightHeight) {
                // Left subtree is perfect
                count += (1 << leftHeight); // Add nodes of left subtree + root
                node = node.right;         // Move to right subtree
            } else {
                // Right subtree is perfect
                count += (1 << rightHeight); // Add nodes of right subtree + root
                node = node.left;           // Move to left subtree
            }
        }

        return count;
    }

    private int getHeight(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left; // Go down the leftmost path
        }
        return height;
    }
}
