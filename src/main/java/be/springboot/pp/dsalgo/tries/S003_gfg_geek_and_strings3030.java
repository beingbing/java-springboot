package be.springboot.pp.dsalgo.tries;

import java.util.ArrayList;
import java.util.List;

public class S003_gfg_geek_and_strings3030 {

    static List<Integer> prefixCount(int N, int Q, String[] list, String[] query) {
        List<Integer> result = new ArrayList<>();
        Trie trie = new Trie();
        for (String word : list) trie.insert(word); // Insert all words into the Trie
        for (String q : query) result.add(trie.countPrefix(q)); // Process each query
        return result;
    }

}
