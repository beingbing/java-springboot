package be.springboot.pp.dsalgo.tries;

import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;
    int prefixCount;
    boolean isEndOfWord;

    TrieNode() {
        children = new HashMap<>();
        prefixCount = 0;
        isEndOfWord = false;
    }
}
