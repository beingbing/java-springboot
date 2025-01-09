package be.springboot.pp.dsalgo.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    public int compareTo(HuffmanNode other) {
        return this.freq < other.freq ? -1: 1;
        // returning 1 when a.freq == b.freq instead of 0 to maintain the insertion order
        // specific to code submission, not a part of hoffman encoding
    }
}

public class S001_gfg_huffman_encoding {
    public ArrayList<String> huffmanCodes(String S, int[] f, int N) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(); // Priority Queue to build the Huffman Tree

        // Create a node for each character and add to priority queue
        for (int i = 0; i < N; i++) pq.add(new HuffmanNode(S.charAt(i), f[i]));

        while (pq.size() > 1) { // Build the Huffman Tree
            // Extract two nodes with the smallest frequency
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            // Create a new internal node with the sum of the two frequencies
            HuffmanNode merged = new HuffmanNode('.', left.freq + right.freq);
            merged.left = left;
            merged.right = right;
            // Add the merged node back into the priority queue
            pq.add(merged);
        }

        HuffmanNode root = pq.poll(); // The root of the tree
        ArrayList<String> result = new ArrayList<>(); // List to store the Huffman Codes
        generateCodes("", root, result); // Generate codes using preorder traversal
        return result;
    }

    private void generateCodes(String code, HuffmanNode node, List<String> result) {
        if (node == null) return;
        if (node.data != '.') result.add(code); // If it's a leaf node, add the code to the result
        generateCodes(code + "0", node.left, result);
        generateCodes(code + "1", node.right, result);
    }
}
