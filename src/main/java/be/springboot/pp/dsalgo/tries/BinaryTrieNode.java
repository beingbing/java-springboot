package be.springboot.pp.dsalgo.tries;

public class BinaryTrieNode {
    BinaryTrieNode[] children;
    int prefixCount; // count of numbers passing through this node

    public BinaryTrieNode() {
        children = new BinaryTrieNode[2];
        prefixCount = 0;
    }
}
