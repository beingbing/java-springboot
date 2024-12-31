package be.springboot.pp.dsalgo.tries;

/*
Problem statement -

Let's define a new data structure - black box. A black box is a data structure that is capable of performing the following operations:

add an integer to the black box
delete an integer from the black box
find the subset from the set of numbers present inside the black box which produce a maximal value after being XORed.
We will give you N queries. Each query is an addition or a deletion operation as mentioned above. After each query we ask you to find the maximal possible XOR that can be obtained by combining some of the numbers that are present in the black box.

Input Format
The first line of input contains an integer N.
Then there is a line with N integers, separated with single spaces. Some of the integers are positive while some are negative.

Let's denote the ith such integer by Ai. If it's positive, then it corresponds to the addition operation: addition of Ai to the black box. Otherwise, it corresponds to the deletion operation: deletion of |Ai| from the black box.

It is guaranteed that:

we will never add a number that is already present in the black box.
we will never delete a number that is currently not present in the black box.

Output Format
After each query, output the maximal XOR in a new line. If the black box has no numbers after the query, output 0.

Constraints
1 ≤ N ≤ 5 * 10^5
0 < |Ai| ≤ 2 * 10^9

* */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// this code didn't work
// couldn't implement removal of binary representation of number correctly

/*
* my implementation -
* void remove(int num) {
            TrieNode current = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                current = current.children[bit];
                current.count--;
            }
        }
* */

public class S002_hr_black_box {

//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder results = new StringBuilder();
//
//        int n = Integer.parseInt(bufferedReader.readLine().trim());
//        int[] operations = new int[n];
//        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine().trim());
//        for (int i = 0; i < n; i++) operations[i] = Integer.parseInt(tokenizer.nextToken());
//
//        BinaryTrie trie = new BinaryTrie();
//        Set<Integer> currentSet = new HashSet<>();
//
//        for (int op : operations) {
//            if (op > 0) {
//                // Add operation
//                currentSet.add(op);
//                trie.insert(op);
//            } else {
//                // Remove operation
//                int num = -op;
//                currentSet.remove(num);
//                trie.remove(num);
//            }
//
//            // Query for maximal XOR
//            if (currentSet.isEmpty()) {
//                results.append(0).append("\n");
//            } else if (currentSet.size() == 1) {
//                for (Integer ele : currentSet) results.append(ele).append("\n");
//            } else {
//                int maxXOR = 0;
//                for (int num : currentSet) {
//                    maxXOR = Math.max(maxXOR, trie.findMaxXOR(num));
//                }
//                results.append(maxXOR).append("\n");
//            }
//        }
//
//        bufferedWriter.write(results.toString());
//        bufferedWriter.flush();
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
}
