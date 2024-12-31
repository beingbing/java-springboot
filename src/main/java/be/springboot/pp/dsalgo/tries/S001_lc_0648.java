package be.springboot.pp.dsalgo.tries;

import java.util.Arrays;
import java.util.List;

public class S001_lc_0648 {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();

        for (String root : dictionary) trie.insert(root); // Build the Trie with dictionary roots

        StringBuilder result = new StringBuilder();
        String[] words = sentence.split(" ");
        for (String word : words) {
            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(trie.findShortestRoot(word));
        }

        return result.toString();
    }
}
