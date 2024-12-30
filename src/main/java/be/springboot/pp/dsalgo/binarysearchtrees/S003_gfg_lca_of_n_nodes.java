package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.ArrayList;

public class S003_gfg_lca_of_n_nodes {

    public TreeNode lcaOfNodes(TreeNode root, ArrayList<Integer> keyNodes) {
        ArrayList<TreeNode> ancestors = new ArrayList<TreeNode>();

        int matchingNodes = 0;
        getKeysCount(root, keyNodes, matchingNodes, ancestors);

        // First Node in the Ancestors list
        // is the LCA of Given keyNodes
        return ancestors.getFirst();
    }

    private int getKeysCount(TreeNode root, ArrayList<Integer> keyNodes, int matchingNodes, ArrayList<TreeNode> ancestors) {
        if (root == null) return 0;
        matchingNodes += getKeysCount(root.left, keyNodes, matchingNodes, ancestors) + getKeysCount(root.right, keyNodes, matchingNodes, ancestors);
        if (keyNodes.contains(root.data)) matchingNodes++; // if current node is also in the list
        if (matchingNodes == keyNodes.size()) ancestors.add(root);
        return matchingNodes;
    }

}
