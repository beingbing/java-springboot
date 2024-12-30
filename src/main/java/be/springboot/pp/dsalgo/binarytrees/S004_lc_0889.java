package be.springboot.pp.dsalgo.binarytrees;

public class S004_lc_0889 {
    private int preIndex = 0;
    private int postIndex = 0;

    public Node constructFromPrePost(int[] preorder, int[] postorder) {
        Node root = new Node(preorder[preIndex++]); // Step 1: Create the root node from the current preorder index

        // Step 2: Check if the current root's value is not equal to the current postorder value
        // If not, construct the left subtree
        if (root.data != postorder[postIndex]) root.left = constructFromPrePost(preorder, postorder);

        // Step 3: Check again if the current root's value is not equal to the current postorder value
        // If not, construct the right subtree
        if (root.data != postorder[postIndex]) root.right = constructFromPrePost(preorder, postorder);
        postIndex++; // Step 4: Move the postorder index forward as the current subtree is processed
        return root; // Return the constructed subtree
    }
}
