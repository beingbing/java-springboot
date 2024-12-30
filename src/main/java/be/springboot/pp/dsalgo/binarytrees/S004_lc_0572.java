package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class S004_lc_0572 {
    public boolean isSubtree(Node root, Node subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(Node root1, Node root2) {
        if (root1 == null && root2 == null) return true; // Both are null
        if (root1 == null || root2 == null) return false; // One is null
        if (root1.data != root2.data) return false; // Values don't match
        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }
}

class S004_lc_0572_recursive {
    public boolean isSubtree(Node root, Node subRoot) {
        List<Integer> preorderTree = new ArrayList<>();
        List<Integer> preorderSubTree = new ArrayList<>();

        preorder(root, preorderTree);
        preorder(subRoot, preorderSubTree);

        int treeSize = preorderTree.size(), subTreeSize = preorderSubTree.size();
        if (treeSize < subTreeSize) return false;

        for (int i = 0; i <= treeSize - subTreeSize; i++) {
            int similarityCount = 0;
            for (int j = i; j < i + subTreeSize; j++)
                if (Objects.equals(preorderTree.get(j), preorderSubTree.get(j - i)))
                    similarityCount++;
            if (similarityCount == subTreeSize) return true;
        }

        return false;
    }

    private void preorder(Node root, List<Integer> preorder) {
        if (root == null) {
            preorder.add(100001);
            return;
        }
        preorder.add(root.data);
        preorder(root.left, preorder);
        preorder(root.right, preorder);
    }
}
