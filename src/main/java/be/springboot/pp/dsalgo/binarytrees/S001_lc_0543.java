package be.springboot.pp.dsalgo.binarytrees;

public class S001_lc_0543 {

    public int diameterOfBinaryTree(Node root) {
        if (root == null) return 0;
        return calculateHeightAndDiameter(root).diameter;
    }

    private HeightAndDiameter calculateHeightAndDiameter(Node node) {
        if (node == null) return new HeightAndDiameter(0, 0); // Base case: If the node is null, height and diameter are 0.
        HeightAndDiameter leftSubtree = calculateHeightAndDiameter(node.left);
        HeightAndDiameter rightSubtree = calculateHeightAndDiameter(node.right);
        int currentHeight = 1 + Math.max(leftSubtree.height, rightSubtree.height);
        int diameterThroughNode = 1 + leftSubtree.height + rightSubtree.height; // add 1 for current node
        int currentDiameter = Math.max(diameterThroughNode, Math.max(leftSubtree.diameter, rightSubtree.diameter));
        return new HeightAndDiameter(currentHeight, currentDiameter);
    }
}

class HeightAndDiameter {
    int height;
    int diameter;

    HeightAndDiameter(int height, int diameter) {
        this.height = height;
        this.diameter = diameter;
    }
}
