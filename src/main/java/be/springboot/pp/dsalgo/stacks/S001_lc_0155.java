package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S001_lc_0155 {
    private final Stack<Pair> stack;

    public S001_lc_0155() {
        stack = new Stack<>();
    }

    public void push(int value) {
        if (stack.isEmpty()) stack.push(new Pair(value, value));
        else {
            int currentMin = stack.peek().minValue;
            int newMin = Math.min(value, currentMin);
            stack.push(new Pair(value, newMin));
        }
    }

    public void pop() {
        if (!stack.isEmpty()) stack.pop();
    }

    public int top() {
        if (!stack.isEmpty()) return stack.peek().value;
        throw new IllegalStateException("Stack is empty");
    }

    public int getMin() {
        if (!stack.isEmpty()) return stack.peek().minValue;
        throw new IllegalStateException("Stack is empty");
    }
}

class Pair {
    int value;    // The element value
    int minValue; // The minimum value seen so far including this element

    Pair(int value, int minValue) {
        this.value = value;
        this.minValue = minValue;
    }
}

class MinStack {
    private Stack<Long> stack; // Stack to store the encoded values
    private long currentMin;   // Variable to track the current minimum value

    public MinStack() {
        stack = new Stack<>();
        currentMin = Long.MAX_VALUE; // Initially set currentMin to the maximum possible value
    }

    // Pushes a value onto the stack while tracking the minimum value. If the value is smaller than the current minimum, an encoded value is stored in the stack.
    public void push(int value) {
        long val = (long) value; // Convert to long to avoid overflow issues

        if (stack.isEmpty()) {
            // If the stack is empty, set the currentMin to the new value and push it
            currentMin = val;
            stack.push(val);
        } else {
            // If the value is smaller than the current minimum:
            // - Encode the value as 2 * val - currentMin
            // - Update currentMin to the new value
            if (val < currentMin) {
                stack.push(2 * val - currentMin); // Push encoded value
                currentMin = val;                // Update the current minimum
            } else {
                // If the value is not smaller, push it directly onto the stack
                stack.push(val);
            }
        }
    }

    /**
     * Removes the top element from the stack while maintaining the minimum value.
     */
    public void pop() {
        if (stack.isEmpty()) throw new IllegalStateException("Stack is empty");

        long topValue = stack.pop();

        // If the popped value is encoded (indicating it held the current minimum),
        // recalculate the previous minimum using the formula: 2 * currentMin - encodedValue
        if (topValue < currentMin) {
            currentMin = 2 * currentMin - topValue;
        }
    }

    /**
     * Retrieves the top element of the stack without removing it.
     * If the top value is encoded, the current minimum is returned.
     *
     * @return The top element of the stack.
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        long topValue = stack.peek();

        // If the top value is encoded, return the current minimum
        if (topValue < currentMin) {
            return (int) currentMin;
        }
        return (int) topValue;
    }

    /**
     * Retrieves the current minimum value in the stack.
     *
     * @return The minimum value in the stack.
     */
    public int getMin() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return (int) currentMin;
    }
}
