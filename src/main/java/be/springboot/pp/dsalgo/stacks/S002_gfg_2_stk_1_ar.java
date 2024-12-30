package be.springboot.pp.dsalgo.stacks;

class TwoStacks {
    private final int[] a; // Array to hold stack elements
    private final int size;  // Total size of the array
    private int top1;  // Top of Stack 1
    private int top2;  // Top of Stack 2

    // Constructor to initialize the array and stack pointers
    public TwoStacks(int n) {
        size = n;
        a = new int[n];
        top1 = -1;               // Stack 1 starts from the beginning
        top2 = size;             // Stack 2 starts from the end
    }

    // Push an element to Stack 1
    public void push1(int x) {
        if (top1 + 1 < top2) {   // Check for available space
            a[++top1] = x;
        } else {
            throw new StackOverflowError("Stack 1 Overflow");
        }
    }

    // Push an element to Stack 2
    public void push2(int x) {
        if (top1 + 1 < top2) {   // Check for available space
            a[--top2] = x;
        } else {
            throw new StackOverflowError("Stack 2 Overflow");
        }
    }

    // Pop an element from Stack 1
    public int pop1() {
        if (top1 >= 0) {
            return a[top1--];
        } else {
            throw new RuntimeException("Stack 1 Underflow");
        }
    }

    // Pop an element from Stack 2
    public int pop2() {
        if (top2 < size) {
            return a[top2++];
        } else {
            throw new RuntimeException("Stack 2 Underflow");
        }
    }

    // Peek the top element of Stack 1
    public int peek1() {
        if (top1 >= 0) {
            return a[top1];
        } else {
            throw new RuntimeException("Stack 1 is Empty");
        }
    }

    // Peek the top element of Stack 2
    public int peek2() {
        if (top2 < size) {
            return a[top2];
        } else {
            throw new RuntimeException("Stack 2 is Empty");
        }
    }

    // Check if Stack 1 is empty
    public boolean isEmpty1() {
        return top1 == -1;
    }

    // Check if Stack 2 is empty
    public boolean isEmpty2() {
        return top2 == size;
    }
}

// Example usage
public class S002_gfg_2_stk_1_ar {
    public static void main(String[] args) {
        TwoStacks stacks = new TwoStacks(10);

        stacks.push1(1);
        stacks.push1(2);
        stacks.push1(3);

        stacks.push2(9);
        stacks.push2(8);
        stacks.push2(7);

        System.out.println("Popped from Stack 1: " + stacks.pop1()); // Output: 3
        System.out.println("Popped from Stack 2: " + stacks.pop2()); // Output: 7
    }
}
