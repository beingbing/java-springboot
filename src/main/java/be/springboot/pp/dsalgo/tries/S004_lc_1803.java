package be.springboot.pp.dsalgo.tries;

public class S004_lc_1803 {
    public int countPairs(int[] nums, int low, int high) {
        BinaryTrie trie = new BinaryTrie(); // Trie keeping prefix XORs
        int result = 0;

        for (int num : nums) {
            // Count pairs for 'low' and 'high' before adding the current number
            result += trie.countLessThan(num, high + 1) - trie.countLessThan(num, low); // doing this step to satisfy i < j condition
            trie.insert(num); // insert jth element
        }

        return result;
    }
}
