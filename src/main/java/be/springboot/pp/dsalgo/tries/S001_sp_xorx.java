package be.springboot.pp.dsalgo.tries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S001_sp_xorx {

    public static int maxXORWithSubarray(int[] arr, int x) {
        BinaryTrie prefixTrie = new BinaryTrie();
        prefixTrie.insert(0); // Insert 0 for subarray starting at index 0

        int prefixXOR = 0;
        int maxResult = Integer.MIN_VALUE;

        for (int num : arr) {
            prefixXOR ^= num; // Update the prefix XOR
            int currentMax = prefixTrie.findMaxXOR(prefixXOR ^ x); // Find max XOR with x
            maxResult = Math.max(maxResult, currentMax);
            prefixTrie.insert(prefixXOR); // Insert the current prefix XOR into the Trie
        }

        return maxResult ^ x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder results = new StringBuilder();

        int numberOfTestCases = Integer.parseInt(bufferedReader.readLine().trim());

        while (numberOfTestCases-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            int arraySize = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());
            int[] a = new int[arraySize];

            tokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            for (int i = 0; i < arraySize; i++) a[i] = Integer.parseInt(tokenizer.nextToken());

            results.append(maxXORWithSubarray(a, x)).append("\n");
        }

        bufferedWriter.write(results.toString());
        bufferedWriter.flush();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
