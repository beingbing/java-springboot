package be.springboot.pp.dsalgo.stacks;

class S001_lc_1381 {
    private int[] stack;         // Array to simulate the stack
    private int[] increment;     // Array to handle lazy increment
    private int maxSize;         // Maximum size of the stack
    private int top;             // Current top of the stack (-1 indicates empty stack)

    // Constructor to initialize the stack with maxSize
    public S001_lc_1381(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.increment = new int[maxSize];
        this.top = -1; // Stack is initially empty
    }

    // Push an element onto the stack if it hasn't reached maxSize
    public void push(int x) {
        if (top < maxSize - 1) {
            top++;
            stack[top] = x; // Add element at the top
        }
    }

    // Pop the top element from the stack after applying pending increments
    public int pop() {
        if (top == -1) {
            return -1; // Stack is empty
        }
        int result = stack[top] + increment[top]; // Apply pending increment
        if (top > 0) {
            increment[top - 1] += increment[top]; // Transfer pending increment
        }
        increment[top] = 0; // Clear current increment
        top--; // Move top pointer
        return result;
    }

    // Increment the bottom k elements by val
    public void increment(int k, int val) {
        int limit = Math.min(k - 1, top); // Only increment up to the current stack size
        if (limit >= 0) {
            increment[limit] += val; // Add lazy increment
        }
    }
}
