package be.springboot.pp.dsalgo.binarytrees;

public class S002_lc_1379 {
    public final Node getTargetCopy(final Node original, final Node cloned, final Node target) {
        if (original == null) return null; // Base case: original node is null
        if (original == target) return cloned; // Corresponding node found in cloned tree

        // Search in the left subtree
        Node leftResult = getTargetCopy(original.left, cloned.left, target);
        if (leftResult != null) return leftResult;

        // Search in the right subtree
        return getTargetCopy(original.right, cloned.right, target);
    }
}
