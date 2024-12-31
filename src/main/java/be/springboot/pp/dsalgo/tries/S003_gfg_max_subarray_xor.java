package be.springboot.pp.dsalgo.tries;

public class S003_gfg_max_subarray_xor {
    public static int maxSubarrayXOR(int n, int[] arr) {
        int maxXOR = 0, prefixXOR = 0;
        BinaryTrie trie = new BinaryTrie();

        trie.insert(0);

        for (int num : arr) {
            prefixXOR ^= num;
            maxXOR = Math.max(maxXOR, trie.findMaxXOR(prefixXOR));
            trie.insert(prefixXOR);
        }

        return maxXOR;
    }
}
