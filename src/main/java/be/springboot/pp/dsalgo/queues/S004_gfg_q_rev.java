package be.springboot.pp.dsalgo.queues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class S004_gfg_q_rev {
    // Function to reverse a queue
    public static Queue<Integer> reverseQueue(Queue<Integer> queue) {
        // Stack to hold elements for reversal
        Stack<Integer> stack = new Stack<>();

        // Step 1: Dequeue all elements from the queue and push them onto the stack
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        // Step 2: Pop all elements from the stack and enqueue them back to the queue
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        return queue; // The queue is now reversed
    }

    public static void main(String[] args) {
        // Example 1
        Queue<Integer> queue1 = new LinkedList<>(Arrays.asList(4, 3, 1, 10, 2, 6));
        System.out.println("Original Queue: " + queue1);
        Queue<Integer> reversedQueue1 = reverseQueue(queue1);
        System.out.println("Reversed Queue: " + reversedQueue1);

        // Example 2
        Queue<Integer> queue2 = new LinkedList<>(Arrays.asList(4, 3, 2, 1));
        System.out.println("Original Queue: " + queue2);
        Queue<Integer> reversedQueue2 = reverseQueue(queue2);
        System.out.println("Reversed Queue: " + reversedQueue2);

        // Example 3
        Queue<Integer> queue3 = new LinkedList<>(Arrays.asList(7, 9, 5, 12, 8));
        System.out.println("Original Queue: " + queue3);
        Queue<Integer> reversedQueue3 = reverseQueue(queue3);
        System.out.println("Reversed Queue: " + reversedQueue3);
    }
}
