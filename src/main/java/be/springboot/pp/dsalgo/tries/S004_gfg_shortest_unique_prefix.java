package be.springboot.pp.dsalgo.tries;

import java.util.ArrayList;
import java.util.List;

public class S004_gfg_shortest_unique_prefix {

    public static String[] findPrefixes(String[] arr, int N) {
        Trie trie = new Trie();

        // Insert all words into the Trie
        for (String word : arr) trie.insert(word);

        // Find unique prefixes for all words
        List<String> result = new ArrayList<>();
        for (String word : arr) result.add(trie.findUniquePrefix(word));

        return result.toArray(String[]::new);
    }

}
