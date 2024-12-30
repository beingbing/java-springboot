package be.springboot.pp.dsalgo.stacks;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

interface NestedInteger {
    // Returns true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // Returns the single integer that this NestedInteger holds, if it holds a single integer.
    // Throws an exception if this NestedInteger holds a nested list.
    Integer getInteger();

    // Returns the nested list that this NestedInteger holds, if it holds a nested list.
    // Throws an exception if this NestedInteger holds a single integer.
    List<NestedInteger> getList();
}

public class S003_lc_0341 implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    // Constructor: Initialize the iterator with the given nested list
    public S003_lc_0341(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        // Push all elements from nestedList onto the stack in reverse order
        for (int i = nestedList.size() - 1; i >= 0; i--) stack.push(nestedList.get(i));
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        // Process the stack until we find an integer or the stack is empty
        while (!stack.isEmpty()) {
            NestedInteger top = stack.peek();
            if (top.isInteger()) {
                // If the top element is an integer, it can be processed
                return true;
            }
            // Otherwise, expand the nested list at the top of the stack
            stack.pop(); // Remove the list
            List<NestedInteger> nestedList = top.getList();
            // Push all elements of the nested list onto the stack in reverse order
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }
        return false; // No more integers to process
    }
}
