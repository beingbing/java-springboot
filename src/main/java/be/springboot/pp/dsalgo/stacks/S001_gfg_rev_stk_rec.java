package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S001_gfg_rev_stk_rec {

    // Function to reverse the stack
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return; // Base case: If stack is empty, nothing to reverse
        }

        // Remove the top element
        int top = stack.pop();

        // Recursively reverse the remaining stack
        reverseStack(stack);

        // Insert the removed element at the bottom
        insertAtBottom(stack, top);
    }

    // Helper function to insert an element at the bottom of the stack
    private static void insertAtBottom(Stack<Integer> stack, int value) {
        if (stack.isEmpty()) {
            stack.push(value); // Base case: If stack is empty, push the value
            return;
        }

        // Remove the top element
        int top = stack.pop();

        // Recursively insert the value at the bottom
        insertAtBottom(stack, value);

        // Push the top element back
        stack.push(top);
    }

    public static void main(String[] args) {
        // Example Input
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        // Reverse the stack
        reverseStack(stack);

        // Print the reversed stack
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
