package be.springboot.pp.dsalgo.tries;

import java.util.Arrays;

public class S002_gfg_min_xor {

    public static int minxorpair(int n, int[] arr) {
        if (n < 2) return 0;
        Arrays.sort(arr); // Sort the array to minimize XOR between consecutive numbers

        int minXOR = Integer.MAX_VALUE;
        BinaryTrie trie = new BinaryTrie();

        for (int i = 0; i < n; i++) { // Insert numbers into Trie and simultaneously find the minimum XOR
            if (i > 0) minXOR = Math.min(minXOR, trie.findMinXOR(arr[i]));
            trie.insert(arr[i]);
        }

        return minXOR;
    }

    public static void main(String[] args) {
        int[] a = {9, 5, 3};
        System.out.println(minxorpair(a.length, a));
    }
}
