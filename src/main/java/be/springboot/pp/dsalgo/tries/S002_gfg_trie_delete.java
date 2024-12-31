package be.springboot.pp.dsalgo.tries;

public class S002_gfg_trie_delete {
    public static void main(String[] args) {
        String superString = "the a there answer any by bye their";
        String key = "the";

        Trie trie = new Trie();

        // Insert all words from the super string into the Trie
        String[] words = superString.split(" ");
        for (String word : words) trie.insert(word);

        boolean isDeleted = trie.remove(key);

        trie.display();
    }
}
