package be.springboot.pp.dsalgo.stacks;

// implement a stack using an array
public class ArrayStack {
    private int top;
    private final int[] a;

    public ArrayStack(int capacity) {
        a = new int[capacity];
        top = -1;
    }

    public void push(int data) {
        if (isFull()) {
            throw new RuntimeException("Stack Overflow");
        }
        a[++top] = data;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow");
        }
        return a[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return a[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == a.length - 1;
    }
}