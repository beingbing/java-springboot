package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class StackUtils {
    // Push an element to the bottom of the stack
    public static void pushBottom(Stack<Integer> stack, int value) {
        // Base case: If stack is empty, push the value
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }

        // Recursively remove the top element
        int top = stack.pop();
        pushBottom(stack, value);

        // Restore the top element
        stack.push(top);
    }

    public static void pushBottomIteration(Stack<Integer> stack, int value) {
        Stack<Integer> tempStack = new Stack<>();

        // Transfer all elements from the original stack to the temporary stack
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }

        // Push the new value to the original stack (now effectively the bottom)
        stack.push(value);

        // Transfer all elements back from the temporary stack to the original stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }
}
