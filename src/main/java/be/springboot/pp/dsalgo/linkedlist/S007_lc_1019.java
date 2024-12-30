package be.springboot.pp.dsalgo.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class S007_lc_1019 {
    public int[] nextLargerNodes(Node head) {
        // Step 1: Convert the linked list to an array
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.data);
            head = head.next;
        }

        int n = values.size();
        int[] result = new int[n]; // Array to store the final results
        Deque<Integer> stack = new ArrayDeque<>(); // Stack to store indices

        // Step 2: Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && values.get(stack.peek()) <= values.get(i)) stack.pop(); // Remove all elements from the stack that are <= current value
            result[i] = stack.isEmpty() ? 0 : values.get(stack.peek()); // If the stack is not empty, the top contains the next greater element
            stack.push(i); // Push the current index onto the stack
        }

        return result; // Return the resulting array
    }
}

class S007_lc_1019_2 {
    public int[] nextLargerNodes(Node head) {
        // Step 1: Initialize the result list and stack
        List<Integer> resultList = new ArrayList<>(); // To store the final results
        Deque<Integer> stack = new ArrayDeque<>(); // Stack to keep track of indices with unresolved "next larger values"

        // Step 2: Traverse the linked list
        Node currentNode = head;
        while (currentNode != null) {
            int currentValue = currentNode.data;

            // Check if there are elements in the stack whose "next larger value" is this node's value
            while (!stack.isEmpty() && resultList.get(stack.peek()) < currentValue)
                resultList.set(stack.pop(), currentValue); // Update the result for the index at the top of the stack

            resultList.add(0); // Add the current value to the result list (initialize it with 0 for now)
            stack.push(resultList.size() - 1); // Push the current index onto the stack to resolve later
            currentNode = currentNode.next; // Move to the next node in the linked list
        }

        // Step 3: All unresolved indices in the stack have no next larger value, leave them as 0
        // (No explicit action required since they are already set to 0 in the result list)

        // Step 4: Convert the result list to an array for final output
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray; // Return the result array
    }
}
