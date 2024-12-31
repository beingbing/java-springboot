package be.springboot.pp.dsalgo.tries;

public class S001_lc_0421 {
    public int findMaximumXOR(int[] nums) {
        BinaryTrie trie = new BinaryTrie();
        for (int num : nums) trie.insert(num);

        int maxResult = 0;
        for (int num : nums) maxResult = Math.max(maxResult, trie.findMaxXOR(num));
        return maxResult;
    }

    public static void main(String[] args) {
        S001_lc_0421 obj = new S001_lc_0421();
        int[] a = {3, 10, 5, 25, 2, 8};
        System.out.println(obj.findMaximumXOR(a));
    }
}
