package be.springboot.pp.dsalgo.queues;

import java.util.LinkedList;
import java.util.Queue;

public class Sorted123Numbers {
    public static void printNumbers(int n) {
        // Queue to generate numbers
        Queue<String> queue = new LinkedList<>();

        // Initialize the queue with 1, 2, 3
        queue.add("1");
        queue.add("2");
        queue.add("3");

        // Generate the first n numbers
        for (int i = 0; i < n; i++) {
            // Dequeue the front element
            String current = queue.poll();

            // Print the current number
            System.out.print(current + " ");

            // Enqueue the next numbers formed by appending 1, 2, 3
            queue.add(current + "1");
            queue.add(current + "2");
            queue.add(current + "3");
        }
    }

    public static void main(String[] args) {
        int n = 10; // Example input
        printNumbers(n);
    }
}
