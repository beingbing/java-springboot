package be.springboot.pp.dsalgo.tries;

import java.util.ArrayList;
import java.util.List;

public class S001_lc_0208 {

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();

        Trie trie = new Trie();
        result.add(null);

        trie.insert("apple");
        result.add(null);
        result.add(String.valueOf(trie.search("apple")));
        result.add(String.valueOf(trie.search("app")));
        result.add(String.valueOf(trie.startsWith("app")));
        trie.insert("app");
        result.add(null);
        result.add(String.valueOf(trie.search("app")));

        System.out.println(result);
    }
}
