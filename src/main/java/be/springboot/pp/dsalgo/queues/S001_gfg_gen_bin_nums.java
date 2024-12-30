package be.springboot.pp.dsalgo.queues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S001_gfg_gen_bin_nums {
    public static List<String> generateBinaryNumbers(int n) {
        // Result list to store binary numbers
        List<String> result = new ArrayList<>();

        // Queue for BFS
        Queue<String> queue = new LinkedList<>();

        // Initialize queue with the first binary number
        queue.add("1");

        // Generate binary numbers up to n
        for (int i = 0; i < n; i++) {
            // Dequeue the front element
            String current = queue.poll();

            // Add the current binary number to the result
            result.add(current);

            // Enqueue the next binary numbers by appending "0" and "1"
            queue.add(current + "0");
            queue.add(current + "1");
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 5; // Example input
        List<String> binaryNumbers = generateBinaryNumbers(n);
        System.out.println(binaryNumbers);
    }
}
